package com.polotechnologies.heroes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.polotechnologies.heroes.dataModels.FavouriteHero

@Dao
interface FavouriteHeroDao{

    @Insert
    fun insert(favouriteHero: FavouriteHero) : Long

    @Query("SELECT * FROM favourite_heroes_table WHERE hero_name LIKE :heroName")
    fun hero(heroName: String?) : LiveData<List<FavouriteHero>>

    @Query("DELETE FROM favourite_heroes_table")
    fun clear() : Int

    @Query("SELECT * FROM favourite_heroes_table ORDER BY hero_id ASC")
    fun favouriteHeroes() : LiveData<List<FavouriteHero>>

}