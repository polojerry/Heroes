package com.polotechnologies.heroes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.polotechnologies.heroes.database.favouriteHero.DaoFavouriteHero
import com.polotechnologies.heroes.database.favouriteHero.FavouriteHero
import com.polotechnologies.heroes.database.typeConverters.*

@Database(entities = [FavouriteHero::class], version = 1, exportSchema = false )
@TypeConverters(PowerStatsConverter::class, BiographyConverter::class, AppearanceConverter::class,
    WorkConverter::class, ConnectionsConverter::class, ImageConverter::class)
abstract class HeroesDatabase : RoomDatabase() {

    abstract val daoFavouriteHero : DaoFavouriteHero

    companion object{
        @Volatile
        private var INSTANCE : HeroesDatabase? = null

        fun getInstance(context: Context) : HeroesDatabase {
            synchronized(this){
                var instance =
                    INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HeroesDatabase::class.java,
                        "friends_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance

            }
        }

    }
}