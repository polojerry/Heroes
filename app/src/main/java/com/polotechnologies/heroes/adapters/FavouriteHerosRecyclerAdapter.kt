package com.polotechnologies.heroes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.heroes.database.favouriteHero.FavouriteHero
import com.polotechnologies.heroes.databinding.ItemFavouriteHeroBinding

class FavouriteHerosRecyclerAdapter(private val onClickListener: OnClickListener) : ListAdapter<FavouriteHero, FavouriteHerosRecyclerAdapter.FavouriteHeroViewHolder>(HeroDiffCallBack()) {

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

        fun bind(favouriteHero: FavouriteHero) {
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

    class OnClickListener(val clickListener : (favouriteHero: FavouriteHero) -> Unit){
        fun onClick(favouriteHero: FavouriteHero) = clickListener(favouriteHero)
    }


    class HeroDiffCallBack : DiffUtil.ItemCallback<FavouriteHero>(){
        override fun areItemsTheSame(oldItem: FavouriteHero, newItem: FavouriteHero): Boolean {
            return oldItem.heroId == newItem.heroId
        }

        override fun areContentsTheSame(oldItem: FavouriteHero, newItem: FavouriteHero): Boolean {
            return oldItem == newItem
        }

    }
}
