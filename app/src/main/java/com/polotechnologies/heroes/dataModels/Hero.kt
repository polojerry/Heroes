package com.polotechnologies.heroes.dataModels

import com.polotechnologies.heroes.dataModels.heroSubModels.*


data class Hero(
    val name :String,
    val powerstats : HeroPowerStats,
    val biography : HeroBiography,
    val appearance : HeroAppearance,
    val work : HeroWork,
    val connections : HeroConnections,
    val image : HeroImage

)