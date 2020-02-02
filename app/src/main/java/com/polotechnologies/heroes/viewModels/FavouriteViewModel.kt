package com.polotechnologies.heroes.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.database.HeroesDatabase
import com.polotechnologies.heroes.database.favouriteHero.DaoFavouriteHero
import com.polotechnologies.heroes.database.favouriteHero.FavouriteHero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouriteViewModel(app: Application, val database: DaoFavouriteHero) : ViewModel() {

    //selected hero
    private val _selectedHero = MutableLiveData<FavouriteHero>()
    val selectedHero: LiveData<FavouriteHero>
        get() = _selectedHero


    val favouriteHero = database.favouriteHeroes()

    fun displaySelectedHero(favouriteHero: FavouriteHero) {
        _selectedHero.value = favouriteHero
    }

    fun displaySelectedHeroComplete() {
        _selectedHero.value = null
    }
}