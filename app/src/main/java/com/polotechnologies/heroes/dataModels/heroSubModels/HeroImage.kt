package com.polotechnologies.heroes.dataModels.heroSubModels

import com.squareup.moshi.Json

data class HeroImage(
    @Json(name = "url") val imageUrl: String
)