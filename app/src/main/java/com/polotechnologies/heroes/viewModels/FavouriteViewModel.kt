package com.polotechnologies.heroes.viewModels

import android.app.Application
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

    private val _favouriteHeroes = MutableLiveData<List<FavouriteHero>>()
    val favouriteHeroes: LiveData<List<FavouriteHero>>
        get() = _favouriteHeroes

    val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
}