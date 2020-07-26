package com.swkang.model.domain.pokesearch

import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates

interface PokemonNavigationHelper {

    fun openMapOfPokemonLocations(coordinates: Array<PokemonMapCoordinates>)

    fun showPokemonDetailPopup(
        pokemonId: Long,
        coordinates: List<PokemonMapCoordinates>
    )

    fun dismissPokemonDetailPopup()

}