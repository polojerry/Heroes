package com.polotechnologies.heroes.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.database.favouriteHero.DaoFavouriteHero
import com.polotechnologies.heroes.database.favouriteHero.FavouriteHero
import kotlinx.coroutines.*

class FavouriteViewModel(app: Application, val database: DaoFavouriteHero) : ViewModel() {

    //selected hero
    private val _selectedHero = MutableLiveData<FavouriteHero>()
    val selectedHero: LiveData<FavouriteHero>
        get() = _selectedHero

    //deleted heros
    private val _deletedHeroes = MutableLiveData<Boolean>()
    val deletedHeroes: LiveData<Boolean>
        get() = _deletedHeroes

    val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val favouriteHero = database.favouriteHeroes()

    fun displaySelectedHero(favouriteHero: FavouriteHero) {
        _selectedHero.value = favouriteHero
    }

    fun displaySelectedHeroComplete() {
        _selectedHero.value = null
    }

    fun clearFavourites() {
        coroutineScope.launch {
            val clearedRows = clearDatabase()
            if(clearedRows>0) _deletedHeroes.value = true
        }

    }

    private suspend fun clearDatabase(): Int {
        var rowCleared = 0
        withContext(Dispatchers.IO){
            rowCleared = database.clear()
        }
        return rowCleared
    }
}