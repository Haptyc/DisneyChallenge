package com.example.disneycodechallenge_filippoborca

import androidx.recyclerview.widget.DiffUtil

class DisneyDiffCallback(private val currentList: List<Renderable>,
                         private val oldList: List<Renderable>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return currentList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == currentList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == currentList[newItemPosition]
    }
}