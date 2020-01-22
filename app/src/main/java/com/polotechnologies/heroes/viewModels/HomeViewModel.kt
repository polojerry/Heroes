package com.polotechnologies.heroes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.network.HeroesApi
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    private val _heroesData = MutableLiveData<String>()

    val heroesData: LiveData<String>
        get() = _heroesData

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        fetchHeroes()
    }


    private fun fetchHeroes() {

        coroutineScope.launch{

            val getHeroDeferred = HeroesApi.retrofitService.getHero()

            try {
                val heroList = getHeroDeferred.await().results
                _heroesData.value = "Superheros = ${heroList.size}"

            }catch (t : Throwable){
                _heroesData.value = "Failure: ${t.message}"
            }
        }

    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}