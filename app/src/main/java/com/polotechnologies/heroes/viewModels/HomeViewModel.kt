package com.polotechnologies.heroes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.network.HeroesApi
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    //Response from Server
    private val _heroStatus = MutableLiveData<String>()

    val heroStatus: LiveData<String>
        get() = _heroStatus


    //List from Server
    private val _heroesData = MutableLiveData<List<Hero>>()

    val heroesData: LiveData<List<Hero>>
        get() = _heroesData


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        fetchHeroes()
    }


    private fun fetchHeroes() {

        coroutineScope.launch {

            val getHeroDeferred = HeroesApi.retrofitService.getHero()

            try {
                val heroList = getHeroDeferred.await().results
                if(heroList.isNotEmpty()){
                    _heroesData.value = heroList
                }


            } catch (t: Throwable) {
                _heroStatus.value = "Failure: ${t.message}"
            }
        }

    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}