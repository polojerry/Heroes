package com.polotechnologies.heroes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouriteViewModel() : ViewModel() {

    private val _favouriteHeroes = MutableLiveData<String>()

    val favouriteHeroes: LiveData<String>
        get() = _favouriteHeroes

    init {
        fetchFavouriteHeroes()
    }

    private fun fetchFavouriteHeroes() {
        _favouriteHeroes.value = "favourite heroes"
    }

    override fun onCleared() {
        _favouriteHeroes.value = ""
    }
}