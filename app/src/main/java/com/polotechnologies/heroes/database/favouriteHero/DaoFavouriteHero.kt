package com.polotechnologies.heroes.database.favouriteHero

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoFavouriteHero{

    @Insert
    fun insert(favouriteHero: FavouriteHero) : Long

    @Query("SELECT * FROM favourite_heroes_table WHERE hero_Id = :heroId")
    fun hero(heroId: Long) : FavouriteHero

    @Query("DELETE FROM favourite_heroes_table")
    fun clear()

    @Query("SELECT * FROM favourite_heroes_table ORDER BY hero_id ASC")
    fun favouriteHeroes() : LiveData<List<FavouriteHero>>

    @Query("SELECT * FROM favourite_heroes_table ORDER BY hero_id DESC LIMIT 1")
    fun getLatest(): FavouriteHero?

}