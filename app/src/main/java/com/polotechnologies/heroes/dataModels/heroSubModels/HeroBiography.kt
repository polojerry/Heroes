package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroBiography(
    @Json(name = "full-name") val fullName: String,
    @Json(name = "alter-egos")val alterEgos: String,
    val aliases: List<String>,
    @Json(name = "place-of-birth")val placeOfBirth: String,
    @Json(name = "first-appearance")val firstAppearance: String,
    val publisher: String,
    val alignment: String
) : Parcelable