package com.swkang.model.domain.pokesearch

import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates
import io.reactivex.rxjava3.core.Single

interface PokemonNavigationHelper {

    fun openMapOfPokemonLocations(coordinates: List<PokemonMapCoordinates>)

    fun showPokemonDetailPopup(
        pokemonId: Long,
        coordinates: List<PokemonMapCoordinates>,
        onPokemonMapsClicked: () -> Unit
    )

    fun dismissPokemonDetailPopup()

}