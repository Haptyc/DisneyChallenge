package com.example.disneycodechallenge_filippoborca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CustomAdapter(private val list:ArrayList<Model>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkbox_layout,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].toString()
        holder.checkBox.setOnClickListener {
            holder.continueButton.isEnabled = holder.checkBox.isChecked

        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var checkBox:CheckBox
        var name: TextView
        var continueButton:Button
        init {
            checkBox = view.findViewById(R.id.checkbox)
            name = view.findViewById(R.id.name)
            continueButton = view.findViewById(R.id.continue_button)
        }
    }
}