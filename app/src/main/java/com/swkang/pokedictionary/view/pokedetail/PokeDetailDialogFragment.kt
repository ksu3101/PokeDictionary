package com.swkang.pokedictionary.view.pokedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.swkang.common.POKEMON_SEARCH_ACTIVITY_SCOPEID
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.pokedetail.PokeDetailViewModel
import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates
import com.swkang.pokedictionary.R
import com.swkang.pokedictionary.base.BaseDialogFragment
import com.swkang.pokedictionary.view.pokemap.PokeMapsActivityArgs
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.scope.getViewModel
import java.lang.IllegalArgumentException

class PokeDetailDialogFragment : BaseDialogFragment() {
    companion object {
        val TAG = "PokeDetailDialogFragment"
        val BUNDLEKEY_COORDINATES = "PokeDetailDialogFragment.Bundle.PokemonMapCoordinates"

        fun newInstance(coordinates: Array<PokemonMapCoordinates>): PokeDetailDialogFragment {
            val fragment = PokeDetailDialogFragment()
            val bundle = Bundle()
            bundle.putParcelableArray("BUNDLEKEY_COORDINATES", coordinates)
            return fragment
        }
    }

    private val activityScope = getKoin().getScope(POKEMON_SEARCH_ACTIVITY_SCOPEID)
    private val vm: PokeDetailViewModel by lazy {
        activityScope.getViewModel<PokeDetailViewModel>(requireParentFragment())
    }
    private val coordinates by navArgs<PokeMapsActivityArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        vm.setPokemonCoordinates(coordinates.pokeMapInfos)
        return view
    }

    override fun getLayoutId(): Int = R.layout.pokedetail_fragment

    override fun createViewModel(): BaseViewModel = vm
}