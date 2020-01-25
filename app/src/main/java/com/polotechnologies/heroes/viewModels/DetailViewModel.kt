package com.polotechnologies.heroes.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polotechnologies.heroes.dataModels.Hero

class DetailViewModel(hero: Hero, app: Application) : AndroidViewModel(app) {

    private val _selectedHero = MutableLiveData<Hero>()

    val selectedHero: LiveData<Hero>
        get() = _selectedHero

    init{
        _selectedHero.value = hero
    }


    override fun onCleared() {
        super.onCleared()
    }
}