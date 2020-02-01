package com.polotechnologies.heroes.database.favouriteHero


import android.os.Parcelable
import androidx.room.*
import com.polotechnologies.heroes.dataModels.heroSubModels.*
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
    val biography: HeroBiography?,

    @ColumnInfo(name = "hero_appearance")
    val appearance: HeroAppearance?,

    @ColumnInfo(name = "hero_work")
    val work: HeroWork?,

    @ColumnInfo(name = "hero_connections")
    val connections: HeroConnections?,

    @ColumnInfo(name = "hero_image")
    val image: HeroImage?

) : Parcelable

