package com.polotechnologies.heroes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polotechnologies.heroes.dataModels.Hero
import com.polotechnologies.heroes.databinding.ItemHeroBinding

class HeroRecyclerAdapter(private val onClickListener: OnClickListener) : ListAdapter<Hero, HeroRecyclerAdapter.HeroViewHolder>(HeroDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(hero)
        }
        holder.bind(hero)

    }


    class HeroViewHolder private constructor(val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.hero = hero
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HeroViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHeroBinding.inflate(layoutInflater, parent, false)
                return HeroViewHolder(binding)
            }
        }

    }

    class OnClickListener(val clickListener : (hero: Hero) -> Unit){
        fun onClick(hero: Hero) = clickListener(hero)
    }


    class HeroDiffCallBack : DiffUtil.ItemCallback<Hero>(){
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }

    }
}