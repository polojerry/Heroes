package com.polotechnologies.heroes.dataModels.heroSubModels

import com.squareup.moshi.Json

data class HeroConnections(
    @Json(name = "group-affiliation") val groupAffiliation: String,
    val relatives: String
)