/*
package com.polotechnologies.heroes.database.favouriteHero

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.polotechnologies.heroes.dataModels.FavouriteHero
import com.polotechnologies.heroes.dataModels.heroSubModels.*
import com.polotechnologies.heroes.database.FavouriteHeroDao
import com.polotechnologies.heroes.database.HeroesDatabase
import junit.framework.Assert.assertEquals
import org.junit.Before

import org.junit.Test
import java.io.IOException

class HeroesDatabaseTest {

    lateinit var heroesFavouriteHeroDao: FavouriteHeroDao
    lateinit var database : HeroesDatabase

    @Before
    fun createDatabase() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, HeroesDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        heroesFavouriteHeroDao = database.favouriteHeroDao
    }

*/
/*    *//*
*/
/*@After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }
*//*

    @Test
    @Throws(IOException::class)
    fun insertFavouriteHero(){
        val heroAppearance  = HeroAppearance("","", listOf(), listOf(), "", "")
        val heroBiography = HeroBiography("","",listOf(),"", "", "","")
        val heroConnections = HeroConnections("", "")
        val heroImage = HeroImage("")
        val heroPowerStat = HeroPowerStats("","","","","","")
        val heroWork = HeroWork("","")

        val favouriteHero  =
            FavouriteHero(
                0L,
                "test",
                heroPowerStat,
                heroBiography,
                heroAppearance,
                heroWork,
                heroConnections,
                heroImage
            )

        heroesFavouriteHeroDao.insert(favouriteHero)

        val selectedFavouriteHero = heroesFavouriteHeroDao.getLatest()

        assertEquals(selectedFavouriteHero?.name, "test")
    }

}*/
