package com.polotechnologies.heroes.adapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.viewModels.HeroApiStatus

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

@BindingAdapter("heroApiStatus")
fun bindStatus(imageView: AppCompatImageView, status: HeroApiStatus?){
    when(status){
        HeroApiStatus.LOADING ->{
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }

        HeroApiStatus.ERROR ->{
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }

        HeroApiStatus.DONE->{
            imageView.visibility = View.GONE
        }


    }
}