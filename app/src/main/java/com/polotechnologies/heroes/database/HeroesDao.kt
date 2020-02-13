package com.polotechnologies.heroes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.polotechnologies.heroes.dataModels.Hero

@Dao
interface HeroesDao {

    @Query("SELECT * FROM heroes_table ORDER BY hero_name ASC")
    fun getHeroes() : LiveData<List<Hero>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg heroes: Hero)
}