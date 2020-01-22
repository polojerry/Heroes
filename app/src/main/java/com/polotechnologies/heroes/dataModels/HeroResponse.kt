package com.polotechnologies.heroes.dataModels

import com.squareup.moshi.Json

data class HeroResponse(
    val response: String,
    @Json(name = "results-for") val response_for: String,
    val results : List<Hero>
)