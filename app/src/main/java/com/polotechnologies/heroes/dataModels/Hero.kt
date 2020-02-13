package com.polotechnologies.heroes.dataModels

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.polotechnologies.heroes.dataModels.heroSubModels.*
import com.polotechnologies.heroes.database.typeConverters.*
import kotlinx.android.parcel.Parcelize
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
@Parcelize
@Entity(tableName = "heroes_table")
data class Hero(
    @PrimaryKey @ColumnInfo(name = "hero_name")
    val name :String,

    @ColumnInfo(name = "hero_power_stats")
    @TypeConverters(PowerStatsConverter::class)
    val powerstats : HeroPowerStats,

    @ColumnInfo(name= "hero_biography")
    @TypeConverters(BiographyConverter::class)
    val biography : HeroBiography,

    @ColumnInfo(name = "hero_appearance")
    @TypeConverters(AppearanceConverter::class)
    val appearance : HeroAppearance,

    @ColumnInfo(name = "hero_work")
    @TypeConverters(WorkConverter::class)
    val work : HeroWork,

    @ColumnInfo(name = "hero_connections")
    @TypeConverters(ConnectionsConverter::class)
    val connections : HeroConnections,

    @ColumnInfo(name = "hero_image")
    @TypeConverters(ImageConverter::class)
    val image : HeroImage

) : Parcelable