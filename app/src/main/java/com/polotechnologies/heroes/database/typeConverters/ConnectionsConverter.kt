package com.polotechnologies.heroes.database.typeConverters

import androidx.room.TypeConverter
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroAppearance
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroBiography
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroConnections
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroWork

class ConnectionsConverter {

    @TypeConverter
    fun toString(heroConnections: HeroConnections?): String {
        var connections = ""

        if (heroConnections == null) {
            return connections
        } else {
            connections = "${heroConnections.groupAffiliation}," +
                    heroConnections.relatives
        }


        return connections

    }

    @TypeConverter
    fun toConnections(connections: String?): HeroConnections? {

        var heroConnections: HeroConnections? = HeroConnections(
            "", ""
        )

        if (connections.equals("")) {

            return heroConnections

        } else {

            val connectionsList: List<String> = connections!!.split(",")

            heroConnections = HeroConnections(
                connectionsList[0],
                connectionsList[1]
            )

            return heroConnections
        }


    }
}