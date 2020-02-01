package com.polotechnologies.heroes.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.database.HeroesDatabase
import com.polotechnologies.heroes.network.HeroesApi
import kotlinx.coroutines.*

class HeroesViewModel(heroName: String?, app: Application) : ViewModel() {

    //Response from Server
    private val _heroStatus = MutableLiveData<HeroApiStatus>()
    val heroStatus: LiveData<HeroApiStatus>
        get() = _heroStatus

    //List from Server
    private val _heroesData = MutableLiveData<List<Hero>>()
    val heroesData: LiveData<List<Hero>>
        get() = _heroesData

    //selected hero
    private val _selectedHero = MutableLiveData<Hero>()
    val selectedHero : LiveData<Hero>
        get () = _selectedHero


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        fetchHeroes(heroName)
    }


    fun fetchHeroes(heroName: String?) {

        coroutineScope.launch {

            val getHeroDeferred = HeroesApi.retrofitService.getHero(heroName)

            try {
                _heroStatus.value = HeroApiStatus.LOADING

                val heroList = getHeroDeferred.await().results

                _heroStatus.value = HeroApiStatus.DONE

                if(heroList.isNotEmpty()){
                    _heroesData.value = heroList
                }


            } catch (t: Throwable) {
                _heroStatus.value = HeroApiStatus.ERROR
                _heroesData.value = ArrayList()
            }
        }

    }

    fun displaySelectedHero(hero : Hero){
        _selectedHero.value = hero
    }

    fun displaySelectedHeroComplete(){
        _selectedHero.value = null
    }


    override fun onCleared() {
        viewModelJob.cancel()
    }
}

public enum class HeroApiStatus{
    LOADING,
    ERROR,
    DONE
}