package com.polotechnologies.heroes.database.typeConverters

import androidx.room.TypeConverter
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroAppearance
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroBiography
import com.polotechnologies.heroes.dataModels.heroSubModels.HeroWork

class WorkConverter {

    @TypeConverter
    fun toString(heroWork: HeroWork?): String {
        var work = ""

        if (heroWork == null) {
            return work
        } else {
            work = "${heroWork.occupation}," +
                    heroWork.base
        }


        return work

    }

    @TypeConverter
    fun toHeroWork(work: String?): HeroWork? {

        var heroWork: HeroWork? = HeroWork(
            "", ""
        )

        if (work.equals("")) {

            return heroWork

        } else {

            val workList: List<String> = work!!.split(",")

            heroWork = HeroWork(
                workList[0],
                workList[1]
            )

            return heroWork
        }


    }
}