package com.polotechnologies.heroes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.network.HeroesApi
import com.polotechnologies.heroes.dataModels.HeroResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _heroesData = MutableLiveData<String>()

    val heroesData: LiveData<String>
        get() = _heroesData

    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {

        HeroesApi.retrofitService.getHero().enqueue(object : Callback<HeroResponse>{
            override fun onFailure(call: Call<HeroResponse>, t: Throwable) {
                _heroesData.value = "Failure: ${t.message}"
            }

            override fun onResponse(call: Call<HeroResponse>, response: Response<HeroResponse>) {
                _heroesData.value = "Superhero ${response.body()?.results}"
            }

        })

    }

    override fun onCleared() {
        _heroesData.value = ""
    }
}