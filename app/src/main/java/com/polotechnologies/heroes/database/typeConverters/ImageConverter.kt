package com.polotechnologies.heroes.database.typeConverters

import androidx.room.TypeConverter
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.dataModels.heroSubModels.*

class ImageConverter {

    @TypeConverter
    fun toString(heroImage: HeroImage?): String {
        var image = ""

        if (heroImage == null) {
            return image
        } else {
            image = heroImage.imageUrl
        }


        return image

    }

    @TypeConverter
    fun toImage(image: String?): HeroImage? {

        var heroImage: HeroImage? = HeroImage(
            ""
        )

        if (image.equals("")) {

            return heroImage

        } else {

            val imageList: List<String> = image!!.split(",")

            heroImage = HeroImage(
                imageList[0]
            )

            return heroImage
        }


    }
}