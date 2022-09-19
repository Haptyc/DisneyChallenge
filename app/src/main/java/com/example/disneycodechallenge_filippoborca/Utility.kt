package com.example.disneycodechallenge_filippoborca

import android.view.View


fun View.makeVis(isVisible: Boolean) {
    if (isVisible)
        visibility = View.VISIBLE
    else
        visibility = View.INVISIBLE
}