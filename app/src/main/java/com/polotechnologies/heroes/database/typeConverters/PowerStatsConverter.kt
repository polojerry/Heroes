package com.polotechnologies.heroes.database.typeConverters

import androidx.room.TypeConverter
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroPowerStats

class PowerStatsConverter {

    @TypeConverter
    fun toString(heroPowerStats: HeroPowerStats?): String {
        var powerStats = ""

        if (heroPowerStats == null) {
            return powerStats
        } else {
            powerStats = "${heroPowerStats.intelligence}," +
                    "${heroPowerStats.strength}," +
                    "${heroPowerStats.speed}," +
                    "${heroPowerStats.durability}," +
                    "${heroPowerStats.power}," +
                    heroPowerStats.combat
        }


        return powerStats

    }

    @TypeConverter
    fun toHeroPowerStats(powerStats: String?): HeroPowerStats? {

        var heroPowerStats: HeroPowerStats? = HeroPowerStats(
            "", "", "", "", "", ""
        )

        if (powerStats.equals("")) {

            return heroPowerStats

        } else {

            val stats: List<String> = powerStats!!.split(",")

            heroPowerStats = HeroPowerStats(
                stats[0],
                stats[1],
                stats[2],
                stats[3],
                stats[4],
                stats[5]
            )

            return heroPowerStats
        }


    }
}