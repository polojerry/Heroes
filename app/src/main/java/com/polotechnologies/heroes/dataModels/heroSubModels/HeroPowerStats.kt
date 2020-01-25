package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroPowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
) : Parcelable