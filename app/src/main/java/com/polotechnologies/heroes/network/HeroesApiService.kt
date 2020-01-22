package com.polotechnologies.heroes.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.polotechnologies.heroes.dataModels.HeroResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val ACCESS_TOKEN = "3458535957521457"
private const val BASE_URL = "https://superheroapi.com/api/$ACCESS_TOKEN/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface HeroesApiService {
    @GET("search/batman")
    fun getHero():
            Deferred<HeroResponse>

}

object HeroesApi {
    val retrofitService: HeroesApiService by lazy {
        retrofit.create(HeroesApiService::class.java)
    }

}