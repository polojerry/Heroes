package com.polotechnologies.heroes.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _heroesData = MutableLiveData<String>()

    val heroesData: LiveData<String>
        get() = _heroesData

    init {
        fetchHeroes()
    }

    private fun fetchHeroes() {
        _heroesData.value = "We can be Heroes"
    }

    override fun onCleared() {
        _heroesData.value = ""
    }
}