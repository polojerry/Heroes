package com.polotechnologies.heroes.adapters

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.polotechnologies.heroes.R
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.viewModels.HeroApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imageView: AppCompatImageView, hero: Hero) {
    hero.image.imageUrl.let {
        Glide.with(imageView.context)
            .load(hero.image.imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("heroName")
fun bindText(textView: AppCompatTextView, hero: Hero) {
    hero.name.let {
        textView.text = hero.name
    }
}

@BindingAdapter("heroApiStatus")
fun bindStatus(imageView: AppCompatImageView, status: HeroApiStatus?) {
    if (status == HeroApiStatus.ERROR) {
        imageView.visibility = View.VISIBLE
        imageView.setImageResource(R.drawable.ic_connection_error)

    }
}

@BindingAdapter("heroApiStatusProgress")
fun bindStatus(progressBar: ProgressBar, status: HeroApiStatus?) {
    when (status) {

        HeroApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }

        HeroApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }

        else -> return
    }
}