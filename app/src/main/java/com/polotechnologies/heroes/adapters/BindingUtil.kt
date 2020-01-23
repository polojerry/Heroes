package com.polotechnologies.heroes.adapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.polotechnologies.heroes.dataModels.Hero

@BindingAdapter("imageUrl")
fun bindImage (imageView: AppCompatImageView, hero : Hero){
    hero.image.imageUrl.let{
        Glide.with(imageView.context)
            .load(hero.image.imageUrl)
            .into(imageView)
    }
}

@BindingAdapter("heroName")
fun bindText (textView: AppCompatTextView, hero : Hero){
    hero.name.let{
        textView.text = hero.name
    }
}