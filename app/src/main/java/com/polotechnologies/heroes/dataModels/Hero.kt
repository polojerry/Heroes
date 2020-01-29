package com.polotechnologies.heroes.dataModels

import android.os.Parcelable
import com.polotechnologies.heroes.dataModels.heroSubModels.*
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class Hero(
    val name :String,
    val powerstats : HeroPowerStats,
    val biography : HeroBiography,
    val appearance : HeroAppearance,
    val work : HeroWork,
    val connections : HeroConnections,
    val image : HeroImage

) : Parcelable