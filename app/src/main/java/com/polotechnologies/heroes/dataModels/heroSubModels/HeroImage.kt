package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroImage(
    @Json(name = "url") val imageUrl: String
) : Parcelable