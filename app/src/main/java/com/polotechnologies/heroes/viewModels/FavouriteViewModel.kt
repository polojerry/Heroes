package com.polotechnologies.heroes.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.database.FavouriteHeroDao
import com.polotechnologies.heroes.dataModels.Hero
import kotlinx.coroutines.*

class FavouriteViewModel(val app: Application, val database: FavouriteHeroDao) : ViewModel() {

    //selected hero
    private val _selectedHero = MutableLiveData<Hero>()
    val selectedHero: LiveData<Hero>
        get() = _selectedHero

    //deleted heroes
    private val _deletedHeroes = MutableLiveData<Boolean>()
    val deletedHeroes: LiveData<Boolean>
        get() = _deletedHeroes

    //favourite heroes
    private val _favouriteHeroes = MutableLiveData<LiveData<List<Hero>>>()
    val favouriteHero: LiveData<List<Hero>>
        get() = _favouriteHeroes.value!!

    val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        _favouriteHeroes.value = database.favouriteHeroes()

    }

    fun displaySelectedHero(favouriteHero: Hero) {
        _selectedHero.value = favouriteHero
    }

    fun displaySelectedHeroComplete() {
        _selectedHero.value = null
    }

    fun clearFavourites() {
        coroutineScope.launch {
            val clearedRows = clearDatabase()
            if (clearedRows > 0) _deletedHeroes.value = true
        }

    }

    private suspend fun clearDatabase(): Int {
        var rowCleared = 0
        withContext(Dispatchers.IO) {
            rowCleared = database.clear()
        }
        return rowCleared
    }

    fun searchHero(query: String?) {
        val appendedQuery = "%${query}%"
        _favouriteHeroes.value = database.hero(appendedQuery)
    }
}