package com.example.disneycodechallenge_filippoborca.API

import com.example.disneycodechallenge_filippoborca.DisneyPerson
import com.google.gson.annotations.SerializedName

data class DisneyResponse(@SerializedName("all_guests") val allGuests:List<DisneyPerson>)