package com.swkang.pokedictionary.view.pokesearch

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.swkang.model.domain.pokesearch.PokemonNavigationHelper
import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates
import com.swkang.pokedictionary.R
import com.swkang.pokedictionary.base.exts.dismissDialogFragment
import com.swkang.pokedictionary.base.exts.showDialogFragment
import com.swkang.pokedictionary.view.pokedetail.PokeDetailDialogFragment
import com.swkang.pokedictionary.view.pokedetail.PokeDetailDialogFragmentArgs

class PokemonNavigationHelperImpl(
    private val activity: AppCompatActivity
) : PokemonNavigationHelper {

    override fun openMapOfPokemonLocations(coordinates: List<PokemonMapCoordinates>) {
        val direction = PokemonSearchFragmentDirections
            .actionPokemonSearchFragmentToMapsActivity(coordinates.toTypedArray())
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
    }

    override fun showPokemonDetailPopup(
        pokemonId: Long,
        coordinates: List<PokemonMapCoordinates>,
        onPokemonMapsClicked: () -> Unit
    ) {
        val direction = PokemonSearchFragmentDirections
            .actionPokemonSearchFragmentToPokeDetailDialogFragment(coordinates.toTypedArray())
        activity.findNavController(R.id.fragmentContainer).navigate(direction)

//        val fragment = PokeDetailDialogFragment.newInstance(coordinates.toTypedArray())
//        fragment.isCancelable = true
//        activity.showDialogFragment(
//            fragment,
//            PokeDetailDialogFragment.TAG
//        )
    }

    override fun dismissPokemonDetailPopup() {
//        activity.dismissDialogFragment(PokeDetailDialogFragment.TAG)
    }

}