package com.example.disneycodechallenge_filippoborca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disneycodechallenge_filippoborca.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), DisneyPersonCheckedCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private val disneyAdapter = DisneyInvitedAdapter(mutableListOf(),this)

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        vm = MainViewModel(compositeDisposable)
    }

    fun initView() {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        binding.inviteeRecycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = disneyAdapter
        }
        setContentView(binding.root)
        binding.cancelButton.setOnClickListener {
            vm.disclaimerDismissed()
        }
    }

    override fun onChanged(dp: DisneyPerson, checked: Boolean) = vm.onChanged(dp, checked)

    override fun onStart() {
        super.onStart()
        vm.getListOfInvited()
    }

    override fun onResume() {
        super.onResume()
        vm.stateObservable.observe(this, this::render)
    }

    fun render(vs: MainViewState) {
        showError(vs.isError)
        this.disneyAdapter.updateList(vs.disneyPersons)
        binding.bannerConstraintLayout
            .makeVis(vs.showDisclaimer)
        binding.continueButton
            .makeVis(!vs.showDisclaimer)
        binding.continueButton.isEnabled = vs.continueButtonEnabled
    }

    private fun showError(error: Boolean) {
        if (error) {
            AlertDialog.Builder(this)
                .setTitle(R.string.network_error)
                .setMessage(R.string.network_error_message)
                .setPositiveButton(R.string.network_error_try_again) { _, _ ->
                    vm.getListOfInvited()
                }
                .setNegativeButton(R.string.network_error_come_back_later) { _, _ ->
                    Log.d(MainActivity::class.java.name, "Coming back later")
                }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}


/*
https://gist.githubusercontent.com/Haptyc/a186440058a8b8f202ece1bc5a1b45b6/raw/060bc7a3a35baab820789f0a3ae3bf42d14c3c3c/peopleinvitelist.json
 */