package com.polotechnologies.heroes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val ACCESS_TOKEN = "3458535957521457"
private const val BASE_URL = "https://superheroapi.com/api/$ACCESS_TOKEN/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface HeroesApiService {
    @GET("search/Batman")
    fun getHero():
            Call<String>

}

object HeroesApi {
    val retrofitService: HeroesApiService by lazy {
        retrofit.create(HeroesApiService::class.java)
    }

}