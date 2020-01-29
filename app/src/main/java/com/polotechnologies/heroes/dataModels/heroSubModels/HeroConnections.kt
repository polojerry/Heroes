package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class HeroConnections(
    @Json(name = "group-affiliation") val groupAffiliation: String,
    val relatives: String
) : Parcelable