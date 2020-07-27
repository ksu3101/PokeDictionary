package com.swkang.pokedictionary.view.pokesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.pokesearch.datas.PokemonName
import com.swkang.pokedictionary.R
import kotlinx.android.synthetic.main.pokesearch_item.view.*

typealias OnItemClicked = (PokemonName) -> Unit

class PokemonSearchItemAdapter(
    private val query: String,
    private val onItemClicked: OnItemClicked
) : ListAdapter<PokemonName, PokemonSearchItemAdapter.PokemonNameViewHolder>(pokemonDiffCallback) {
    companion object {
        val pokemonDiffCallback = object : DiffUtil.ItemCallback<PokemonName>() {
            override fun areItemsTheSame(oldItem: PokemonName, newItem: PokemonName): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PokemonName, newItem: PokemonName): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonNameViewHolder {
        return PokemonNameViewHolder.create(parent, query, onItemClicked)
    }

    override fun onBindViewHolder(holder: PokemonNameViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    /**
     * View Holder class
     */
    class PokemonNameViewHolder(
        view: View,
        val query: String,
        val onItemClicked: OnItemClicked
    ) : RecyclerView.ViewHolder(view) {

        fun bind(pokemonName: PokemonName) {
            itemView.pokemonSearchName.text = pokemonName.korName
            itemView.setOnClickListener { onItemClicked(pokemonName) }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                query: String,
                onItemClicked: OnItemClicked
            ): PokemonNameViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.pokesearch_item, parent, false)
                return PokemonNameViewHolder(view, query, onItemClicked)
            }
        }
    }

}