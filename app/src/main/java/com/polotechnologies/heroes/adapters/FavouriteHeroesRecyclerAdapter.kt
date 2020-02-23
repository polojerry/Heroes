package com.polotechnologies.heroes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.databinding.ItemFavouriteHeroBinding

class FavouriteHeroesRecyclerAdapter(private val onClickListener: OnClickListener) : ListAdapter<Hero, FavouriteHeroesRecyclerAdapter.FavouriteHeroViewHolder>(HeroDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteHeroViewHolder {
        return FavouriteHeroViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavouriteHeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(hero)
        }
        holder.bind(hero)

    }


    class FavouriteHeroViewHolder private constructor(val binding: ItemFavouriteHeroBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favouriteHero: Hero) {
            binding.favouriteHero = favouriteHero
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FavouriteHeroViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFavouriteHeroBinding.inflate(layoutInflater, parent, false)
                return FavouriteHeroViewHolder(binding)
            }
        }

    }

    class OnClickListener(val clickListener : (hero: Hero) -> Unit){
        fun onClick(favouriteHero: Hero) = clickListener(favouriteHero)
    }


    class HeroDiffCallBack : DiffUtil.ItemCallback<Hero>(){
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.name== newItem.name
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }

    }
}
