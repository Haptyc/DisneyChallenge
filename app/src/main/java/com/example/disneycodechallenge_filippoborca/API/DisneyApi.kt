package com.example.disneycodechallenge_filippoborca.API

import com.example.disneycodechallenge_filippoborca.DisneyPerson
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface DisneyApi {
    @GET ("Haptyc/a186440058a8b8f202ece1bc5a1b45b6/raw/060bc7a3a35baab820789f0a3ae3bf42d14c3c3c/peopleinvitelist.json")
    fun getDisneyList(): Single<DisneyResponse>
}