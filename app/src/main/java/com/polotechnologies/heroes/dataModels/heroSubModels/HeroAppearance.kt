package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class HeroAppearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    @Json(name = "eye-color") val eyeColor : String,
    @Json(name = "hair-color") val hairColor: String

) : Parcelable