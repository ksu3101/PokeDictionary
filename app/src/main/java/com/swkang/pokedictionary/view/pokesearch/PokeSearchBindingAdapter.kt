package com.swkang.pokedictionary.view.pokesearch

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.pokesearch.datas.PokemonName

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