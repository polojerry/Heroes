package com.polotechnologies.heroes.adapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

@BindingAdapter("heroAliases")
fun bindText(textView: AppCompatTextView, hero: Hero) {
    var aliases = ""

    for (alias in hero.biography.aliases) {
        aliases += alias
    }

    aliases = if (aliases == "") "No aliases found." else aliases
    textView.text = aliases
}

@BindingAdapter("heroApiStatus")
fun bindStatus(imageView: AppCompatImageView, status: HeroApiStatus?) {
    when (status) {
        HeroApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
        HeroApiStatus.NONE -> {
            imageView.visibility = View.VISIBLE
        }
        else -> {
            imageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("heroApiStatus")
fun bindStatus(textView: AppCompatTextView, status: HeroApiStatus?) {
    when (status) {
        HeroApiStatus.NONE -> {
            textView.visibility = View.VISIBLE
        }
        else->{
            textView.visibility = View.GONE
        }
    }
}

@BindingAdapter("heroApiStatusProgress")
fun bindStatus(swipeRefreshLayout: SwipeRefreshLayout, status: HeroApiStatus?) {
    when (status) {

        HeroApiStatus.LOADING -> {
            swipeRefreshLayout.isRefreshing = true
        }

        else -> {
            swipeRefreshLayout.isRefreshing = false
        }
    }
}