package com.polotechnologies.heroes.viewModels

import android.app.Application
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.database.FavouriteHeroDao
import kotlinx.coroutines.*

class DetailViewModel(hero: Hero, val app: Application, val database: FavouriteHeroDao) :
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
            val status = saveFavouriteHero(hero!!)
            _statusAddToFavourite.value = status > 0
        }
    }

    private suspend fun saveFavouriteHero(hero: Hero): Long {
        var insertId = 0L

        withContext(Dispatchers.IO) {
            insertId = database.insert(hero)
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

    fun animateArrow(view: AppCompatImageView, description: String) {
        when(description){
            app.resources.getString(R.string.tittle_biography)-> animateBiography(view)
            app.resources.getString(R.string.tittle_work)-> animateWork(view)
            app.resources.getString(R.string.tittle_connection)->animateConnections(view)
        }

    }

    private fun animateBiography(view: AppCompatImageView) {
        if(_isBiographyExpanded.value == false){
            animateArrowDownUp(view)
        }else{
            animateArrowUpDown(view)
        }
    }

    private fun animateWork(view: AppCompatImageView) {
        if(_isWorkExpanded.value == false){
            animateArrowDownUp(view)
        }else{
            animateArrowUpDown(view)
        }
    }

    private fun animateConnections(view: AppCompatImageView) {
        if(_isConnectionsExpanded.value == false){
            animateArrowDownUp(view)
        }else{
            animateArrowUpDown(view)
        }
    }

    private fun animateArrowUpDown(view: AppCompatImageView) {
        view.setImageResource(R.drawable.avd_arrow_down_up)
        val avdArrowDownUp = view.drawable as AnimatedVectorDrawableCompat
        avdArrowDownUp.start()
    }

    private fun animateArrowDownUp(view: AppCompatImageView) {
        view.setImageResource(R.drawable.avd_arrow_up_down)
        val avdArrowDownUp = view.drawable as AnimatedVectorDrawableCompat
        avdArrowDownUp.start()
    }
}