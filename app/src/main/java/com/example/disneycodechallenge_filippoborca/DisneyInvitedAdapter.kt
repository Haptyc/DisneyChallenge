package com.example.disneycodechallenge_filippoborca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge_filippoborca.databinding.CheckboxLayoutBinding
import com.example.disneycodechallenge_filippoborca.databinding.VhHasResHeaderBinding
import com.example.disneycodechallenge_filippoborca.databinding.VhResNeededFooterBinding
import com.example.disneycodechallenge_filippoborca.databinding.VhResNeededHeaderBinding

class DisneyInvitedAdapter(
    private val list: MutableList<Renderable> = mutableListOf(),
    private val onChange: DisneyPersonCheckedCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DisneyViewTypes.INVITED_HEADER.num -> InvitedHeaderViewHolder(
                VhHasResHeaderBinding.inflate(
                    inflater
                )
            )
            DisneyViewTypes.NOT_INVITED_HEADER.num -> NotInvitedHeaderViewHolder(
                VhResNeededHeaderBinding.inflate(inflater)
            )
            DisneyViewTypes.NOT_INVITED_FOOTER.num -> NotInvitedFooterViewHolder(
                VhResNeededFooterBinding.inflate(inflater)
            )
            else -> PersonViewHolder(CheckboxLayoutBinding.inflate(inflater), onChange)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data: Renderable = list[position]
        when (getItemViewType(position)) {
            DisneyViewTypes.INVITED_HEADER.num -> (holder as InvitedHeaderViewHolder).bind(data as DisneyBanner)
            DisneyViewTypes.NOT_INVITED_HEADER.num -> (holder as NotInvitedHeaderViewHolder).bind(
                data as DisneyBanner
            )
            DisneyViewTypes.NOT_INVITED_FOOTER.num -> (holder as NotInvitedFooterViewHolder).bind(
                data as DisneyBanner
            )
            DisneyViewTypes.DISNEY_PERSON.num -> (holder as PersonViewHolder).bind(data as DisneyPerson)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (val a = list[position]) {
            is DisneyBanner -> a.bannerType.num
            else -> DisneyViewTypes.DISNEY_PERSON.num
        }
    }

    fun updateList(disneyPersons: List<Renderable>) {
        val diffCallback = DisneyDiffCallback(
            disneyPersons,
            list
        )
        val result = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(disneyPersons)
        result.dispatchUpdatesTo(this)
    }

    class PersonViewHolder(
        val binding: CheckboxLayoutBinding,
        val cb: DisneyPersonCheckedCallback
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(renderable: DisneyPerson) {
            binding.checkbox.setOnCheckedChangeListener(null)
            binding.name.text = renderable.name
            binding.checkbox.isChecked = renderable.isChecked
            binding.checkbox.setOnCheckedChangeListener { _, checked ->
                cb.onChanged(renderable, checked)
            }

        }
    }

    class InvitedHeaderViewHolder(val binding: VhHasResHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(renderable: DisneyBanner) {
            //hardcoded
        }
    }

    class NotInvitedHeaderViewHolder(val binding: VhResNeededHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(renderable: DisneyBanner) {
            //hardcoded
        }
    }

    class NotInvitedFooterViewHolder(val binding: VhResNeededFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(renderable: DisneyBanner) {
            //hardcoded
        }
    }
}

interface DisneyPersonCheckedCallback {
    fun onChanged(dp: DisneyPerson, checked: Boolean)
}