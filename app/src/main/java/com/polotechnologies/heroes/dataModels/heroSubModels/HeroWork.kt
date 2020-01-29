package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class HeroWork(
    val occupation: String,
    val base: String
) : Parcelable