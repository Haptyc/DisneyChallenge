package com.example.disneycodechallenge_filippoborca

import com.google.gson.annotations.SerializedName

data class DisneyPerson(
    val name: String,
    @SerializedName("invited") val isInvited: Boolean,
    val isChecked: Boolean = false
): Renderable

data class MainViewState(
    val disneyPersons: List<Renderable> = listOf(),
    val isError: Boolean = false,
    val showDisclaimer: Boolean = false,
    val continueButtonEnabled: Boolean = false
)

data class DisneyBanner(val bannerType: DisneyViewTypes = DisneyViewTypes.INVITED_HEADER): Renderable

enum class DisneyViewTypes(val num: Int) {
    INVITED_HEADER(0),
    NOT_INVITED_HEADER(1),
    NOT_INVITED_FOOTER(2),
    DISNEY_PERSON(3)

}

interface Renderable