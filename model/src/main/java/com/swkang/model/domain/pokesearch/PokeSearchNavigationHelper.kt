package com.swkang.model.domain.pokesearch

import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates

interface PokeSearchNavigationHelper {

    fun openMapOfPokemonLocations(coordinates: PokemonMapCoordinates)

}