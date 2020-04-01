package com.polotechnologies.heroes.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.database.HeroesDatabase
import com.polotechnologies.heroes.network.HeroesApi
import kotlinx.coroutines.*
import java.lang.Exception

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

    init{
        _heroStatus.value = HeroApiStatus.NONE
    }


    fun fetchHeroes(heroName: String?) {

        coroutineScope.launch {

            val getHeroDeferred = HeroesApi.retrofitService.getHero(heroName)

            try {
                _heroStatus.value = HeroApiStatus.LOADING

                val queryResult = getHeroDeferred.await()

                val heroList = queryResult.results

                _heroStatus.value = HeroApiStatus.DONE

                if(heroList.isNotEmpty()){
                    _heroesData.value = heroList
                }

            } catch (t: Throwable) {
                _heroesData.value = ArrayList()
                if(t.message!! == "Unable to resolve host \"superheroapi.com\": No address associated with hostname"){
                    _heroStatus.value = HeroApiStatus.NO_INTERNET_CONNECTION
                }else if(t.message == "The following properties were null: response_for (JSON name results-for), results (at path \$)"){
                    _heroStatus.value = HeroApiStatus.NO_MATCH
                }

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
    NO_INTERNET_CONNECTION,
    DONE,
    NONE,
    NO_MATCH
}