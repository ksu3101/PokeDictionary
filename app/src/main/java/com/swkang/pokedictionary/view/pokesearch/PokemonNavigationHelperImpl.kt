package com.swkang.pokedictionary.view.pokesearch

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.swkang.model.domain.pokesearch.PokemonNavigationHelper
import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates
import com.swkang.pokedictionary.R
import com.swkang.pokedictionary.view.pokedetail.PokeDetailDialogFragmentDirections

class PokemonNavigationHelperImpl(
    private val activity: AppCompatActivity
) : PokemonNavigationHelper {

    override fun openMapOfPokemonLocations(coordinates: Array<PokemonMapCoordinates>) {
        val direction = PokeDetailDialogFragmentDirections
            .actionPokeDetailDialogFragmentToMapsActivity(coordinates)
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
    }

    override fun showPokemonDetailPopup(
        pokemonId: Long,
        coordinates: List<PokemonMapCoordinates>
    ) {
        val direction = PokemonSearchFragmentDirections
            .actionPokemonSearchFragmentToPokeDetailDialogFragment(
                coordinates.toTypedArray(),
                pokemonId
            )
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
    }

    override fun dismissPokemonDetailPopup() {
        activity.findNavController(R.id.fragmentContainer).navigateUp()
    }

}