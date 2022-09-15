package com.example.disneycodechallenge_filippoborca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SecondCustomAdapter(private val list:ArrayList<Model>): RecyclerView.Adapter<SecondCustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.second_checkbox_layout,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SecondCustomAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].toString()
        holder.continueButton.setOnClickListener{
            if(holder.checkBox.isChecked && !holder.checkBoxTwo.isChecked){
                holder.continueButton.isEnabled = true
            } else holder.continueButton.isEnabled = !holder.checkBox.isChecked && holder.checkBoxTwo.isChecked

        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var checkBoxTwo: CheckBox
        var checkBox: CheckBox
        var name: TextView
        var continueButton: Button
        init {
            checkBoxTwo = view.findViewById(R.id.checkbox_two)
            checkBox = view.findViewById(R.id.checkbox)
            name = view.findViewById(R.id.name_two)
            continueButton = view.findViewById(R.id.continue_button)
        }
    }
}