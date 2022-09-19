package com.example.disneycodechallenge_filippoborca.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val baseUrl = "https://gist.githubusercontent.com/"
    private var retrofit: Retrofit? = null
    private var _lazyApi: DisneyApi? = null
    public val API: DisneyApi
        get() {
            if (retrofit == null) {
                retrofit = createRetrofitClient()
            }
            if (_lazyApi == null) {
                _lazyApi = retrofit!!.create(DisneyApi::class.java)
            }
            return _lazyApi!!
        }

    private fun createApiClient() {

    }

    private fun createRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


}