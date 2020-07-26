package com.swkang.pokedictionary.view.pokesearch

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swkang.model.domain.pokesearch.datas.PokemonName
import com.swkang.pokedictionary.R

@BindingAdapter(value = ["query", "pokemons", "onItemClicked"])
fun setUpPokemonNames(
    rv: RecyclerView,
    query: String,
    pokemons: List<PokemonName>,
    onItemClicked: OnItemClicked
) {
    val adapter = PokemonSearchItemAdapter(query, onItemClicked)
    rv.setHasFixedSize(true)
    rv.adapter = adapter
    adapter.submitList(pokemons)
}

@BindingAdapter("pokemonSprite")
fun loadPokemonSpriteeUrl(iv: ImageView, spriteUrl: String?) {
    if (spriteUrl.isNullOrEmpty()) {
        iv.setImageDrawable(null)
    } else {
        Glide.with(iv.context)
            .load(spriteUrl)
            .placeholder(R.drawable.pokemon_spr_placeholder)
            .into(iv)
    }
}