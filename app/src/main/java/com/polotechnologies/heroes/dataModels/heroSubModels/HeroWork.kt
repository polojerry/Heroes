package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroWork(
    val occupation: String,
    val base: String
) : Parcelable