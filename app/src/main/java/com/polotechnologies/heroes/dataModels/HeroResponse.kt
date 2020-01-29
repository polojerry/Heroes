package com.polotechnologies.heroes.dataModels

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class HeroResponse(
    val response: String,
    @Json(name = "results-for") val response_for: String,
    val results : List<Hero>
)