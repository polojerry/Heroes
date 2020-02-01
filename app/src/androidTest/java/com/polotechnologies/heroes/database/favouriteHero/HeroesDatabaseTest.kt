package com.polotechnologies.heroes.database.favouriteHero

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.polotechnologies.heroes.dataModels.heroSubModels.*
import com.polotechnologies.heroes.database.HeroesDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before

import org.junit.Test
import java.io.IOException

class HeroesDatabaseTest {

    lateinit var heroesDaoFavouriteHero: DaoFavouriteHero
    lateinit var database : HeroesDatabase

    @Before
    fun createDatabase() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, HeroesDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        heroesDaoFavouriteHero = database.daoFavouriteHero
    }

/*    *//*@After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }
*/
    @Test
    @Throws(IOException::class)
    fun insertFavouriteHero(){
        val heroAppearance  = HeroAppearance("","", listOf(), listOf(), "", "")
        val heroBiography = HeroBiography("","",listOf(),"", "", "","")
        val heroConnections = HeroConnections("", "")
        val heroImage = HeroImage("")
        val heroPowerStat = HeroPowerStats("","","","","","")
        val heroWork = HeroWork("","")

        val favouriteHero  = FavouriteHero(0L,
            "test",
            heroPowerStat,
            heroBiography,
            heroAppearance,
            heroWork,
            heroConnections,
            heroImage)

        heroesDaoFavouriteHero.insert(favouriteHero)

        val selectedFavouriteHero = heroesDaoFavouriteHero.getLatest()

        assertEquals(selectedFavouriteHero?.name, "test")
    }

}