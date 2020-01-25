package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroConnections(
    @Json(name = "group-affiliation") val groupAffiliation: String,
    val relatives: String
) : Parcelable