package com.polotechnologies.heroes.database.typeConverters

import androidx.room.TypeConverter
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroAppearance
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroBiography

class AppearanceConverter {

    @TypeConverter
    fun toString(heroAppearance: HeroAppearance?): String {
        var appearance = ""

        if (heroAppearance == null) {
            return appearance
        } else {
            appearance = "${heroAppearance.gender}," +
                    "${heroAppearance.race}," +
                    "${heroAppearance.height}," +
                    "${heroAppearance.weight}," +
                    "${heroAppearance.eyeColor}," +
                    heroAppearance.hairColor
        }


        return appearance

    }

    @TypeConverter
    fun toHeroAppearance(appearance: String?): HeroAppearance? {

        var heroAppearance: HeroAppearance? = HeroAppearance(
            "", "", listOf(), listOf(), "",""
        )

        if (appearance.equals("")) {

            return heroAppearance

        } else {

            val appearanceList: List<String> = appearance!!.split(",")

            heroAppearance = HeroAppearance(
                appearanceList[0],
                appearanceList[1],
                listOf(appearanceList[2]),
                listOf(appearanceList[3]),
                appearanceList[4],
                appearanceList[5]
            )

            return heroAppearance
        }


    }
}