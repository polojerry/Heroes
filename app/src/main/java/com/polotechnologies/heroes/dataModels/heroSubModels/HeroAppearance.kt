package com.polotechnologies.heroes.dataModels.heroSubModels

import com.squareup.moshi.Json

data class HeroAppearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    @Json(name = "eye-color") val eyeColor : String,
    @Json(name = "hair-color") val hairColor: String

)