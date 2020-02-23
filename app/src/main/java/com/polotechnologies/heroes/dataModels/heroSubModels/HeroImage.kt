package com.polotechnologies.heroes.dataModels.heroSubModels

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
data class HeroImage(
    @Json(name = "url") val imageUrl: String
) : Parcelable