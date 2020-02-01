package com.polotechnologies.heroes.database.favouriteHero


import android.os.Parcelable
import androidx.room.*
import com.polotechnologies.heroes.dataModels.heroSubModels.*
import com.polotechnologies.heroes.database.typeConverters.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favourite_heroes_table")
data class FavouriteHero(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "hero_id")
    val heroId: Long = 0L,

    @ColumnInfo(name = "hero_name")
    val name: String?,

    @ColumnInfo(name = "hero_power_stats")
    @TypeConverters(PowerStatsConverter::class)
    val powerstats: HeroPowerStats?,

    @ColumnInfo(name = "hero_biography")
    @TypeConverters(BiographyConverter::class)
    val biography: HeroBiography?,

    @ColumnInfo(name = "hero_appearance")
    @TypeConverters(AppearanceConverter::class)
    val appearance: HeroAppearance?,

    @ColumnInfo(name = "hero_work")
    @TypeConverters(WorkConverter::class)
    val work: HeroWork?,

    @ColumnInfo(name = "hero_connections")
    @TypeConverters(ConnectionsConverter::class)
    val connections: HeroConnections?,

    @ColumnInfo(name = "hero_image")
    @TypeConverters(ImageConverter::class)
    val image: HeroImage?

) : Parcelable

