package com.example.disneycodechallenge_filippoborca

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.disneycodechallenge_filippoborca.API.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val compositeDisposable: CompositeDisposable): ViewModel(), DisneyPersonCheckedCallback {

    val stateObservable: MutableLiveData<MainViewState> = MutableLiveData(MainViewState())
    val state : MainViewState
        get() = stateObservable.value!!

    fun getListOfInvited() {
        ApiClient.API.getDisneyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->

                    val isInvitedList = result.allGuests.filter { it.isInvited }
                        .sortedBy { it.name }
                    val isNotInvitedList = result.allGuests.filter { !it.isInvited }
                        .sortedBy { it.name }
                    val allPeopleSorted = mutableListOf<Renderable>()
                    allPeopleSorted.add(DisneyBanner(DisneyViewTypes.INVITED_HEADER))
                    allPeopleSorted.addAll(isInvitedList)
                    allPeopleSorted.add(DisneyBanner(DisneyViewTypes.NOT_INVITED_HEADER))
                    allPeopleSorted.addAll(isNotInvitedList)
                    allPeopleSorted.add(DisneyBanner(DisneyViewTypes.NOT_INVITED_FOOTER))
                    state.copy(
                        disneyPersons = allPeopleSorted,
                        isError = false
                    ).update()
                },{throwable ->
                    state.copy(
                        isError = true
                    ).update()

                    Log.e("Main Activity","error arisen", throwable )
                })
            .let { compositeDisposable.add(it) }
    }

    override fun onChanged(changedPerson: DisneyPerson, checked: Boolean) {
        val persons = state.disneyPersons.map {oldPerson ->
            if (oldPerson == changedPerson) {
                return@map changedPerson.copy(isChecked = checked)
            }
            return@map oldPerson
        }
        val continueEnabled = persons.filter {
            it is DisneyPerson && it.isChecked && it.isInvited
        }.isNotEmpty()

        state.copy(
            disneyPersons = persons,
            continueButtonEnabled = continueEnabled,
            showDisclaimer = !continueEnabled || state.showDisclaimer
        ).update()
    }

    private fun MainViewState.update() {
        stateObservable.postValue(this)
    }

    fun disclaimerDismissed() {
        state
            .copy(
                showDisclaimer = false
            ).update()
    }
}