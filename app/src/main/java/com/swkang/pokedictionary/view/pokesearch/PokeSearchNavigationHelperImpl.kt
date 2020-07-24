package com.swkang.pokedictionary.view.pokesearch

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.swkang.model.domain.pokesearch.PokeSearchNavigationHelper
import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates
import com.swkang.pokedictionary.R

class PokeSearchNavigationHelperImpl(
    private val activity: AppCompatActivity
): PokeSearchNavigationHelper {

    override fun openMapOfPokemonLocations(coordinates: PokemonMapCoordinates) {
        val direction = PokemonSearchFragmentDirections
            .actionPokemonSearchFragmentToMapsActivity(coordinates)
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
    }

}