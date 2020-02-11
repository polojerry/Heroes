package com.polotechnologies.heroes.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.database.favouriteHero.DaoFavouriteHero
import com.polotechnologies.heroes.database.favouriteHero.FavouriteHero
import kotlinx.coroutines.*

class DetailViewModel(hero: Hero, app: Application, val database: DaoFavouriteHero) :
    AndroidViewModel(app) {

    private val _isBiographyExpanded = MutableLiveData<Boolean>()
    val isBiographyExpanded: LiveData<Boolean>
        get() = _isBiographyExpanded

    private val _isWorkExpanded = MutableLiveData<Boolean>()
    val isWorkExpanded: LiveData<Boolean>
        get() = _isWorkExpanded

    private val _isConnectionsExpanded = MutableLiveData<Boolean>()
    val isConnectionsExpanded: LiveData<Boolean>
        get() = _isConnectionsExpanded

    private val _selectedHero = MutableLiveData<Hero>()
    val selectedHero: LiveData<Hero>
        get() = _selectedHero

    private val _statusAddToFavourite = MutableLiveData<Boolean>()
    val statusAddToFavourite: LiveData<Boolean>
        get() = _statusAddToFavourite

    init {
        _selectedHero.value = hero
        _isBiographyExpanded.value = false
        _isWorkExpanded.value = false
        _isWorkExpanded.value = false
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun addToFavourites() {

        coroutineScope.launch {
            val hero = _selectedHero.value
            val favouriteHero = FavouriteHero(
                0L,
                hero?.name,
                hero?.powerstats,
                hero?.biography,
                hero?.appearance,
                hero?.work,
                hero?.connections,
                hero?.image
            )

            val status = saveFavouriteHero(favouriteHero)
            _statusAddToFavourite.value = status > 0
        }
    }

    private suspend fun saveFavouriteHero(favouriteHero: FavouriteHero): Long {
        var insertId = 0L

        withContext(Dispatchers.IO) {
            insertId = database.insert(favouriteHero)
        }

        return insertId

    }

    override fun onCleared() {
        viewModelJob.cancel()
    }

    fun bibliographyCardAction(){
        _isBiographyExpanded.value = _isBiographyExpanded.value == false
    }

    fun workCardAction(){
        _isWorkExpanded.value = _isWorkExpanded.value == false
    }

    fun connectionsCardAction(){
        _isConnectionsExpanded.value = _isConnectionsExpanded.value == false
    }
}