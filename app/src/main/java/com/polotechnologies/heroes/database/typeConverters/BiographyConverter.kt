package com.polotechnologies.heroes.database.typeConverters

import androidx.room.TypeConverter
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroBiography

class BiographyConverter {

    @TypeConverter
    fun toString(heroBiography: HeroBiography?): String {
        var biography = ""

        if (heroBiography == null) {
            return biography
        } else {
            biography = "${heroBiography.fullName}," +
                    "${heroBiography.alterEgos}," +
                    "${heroBiography.aliases}," +
                    "${heroBiography.placeOfBirth}," +
                    "${heroBiography.firstAppearance}," +
                    "${heroBiography.publisher}," +
                    heroBiography.alignment
        }


        return biography

    }

    @TypeConverter
    fun toHeroBiography(biography: String?): HeroBiography? {

        var heroBiography: HeroBiography? = HeroBiography(
            "", "", listOf(), "", "","", ""
        )

        if (biography.equals("")) {

            return heroBiography

        } else {

            val biographyList: List<String> = biography!!.split(",")

            heroBiography = HeroBiography(
                biographyList[0],
                biographyList[1],
                listOf(biographyList[2]),
                biographyList[3],
                biographyList[4],
                biographyList[5],
                biographyList[6]
            )

            return heroBiography
        }


    }
}