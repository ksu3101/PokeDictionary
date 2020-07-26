package com.swkang.pokedictionary.view.pokedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.swkang.common.POKEMON_SEARCH_ACTIVITY_SCOPEID
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.pokedetail.PokeDetailViewModel
import com.swkang.pokedictionary.R
import com.swkang.pokedictionary.base.BaseDialogFragment
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.scope.getViewModel

class PokeDetailDialogFragment : BaseDialogFragment() {
    private val activityScope = getKoin().getScope(POKEMON_SEARCH_ACTIVITY_SCOPEID)
    private val vm: PokeDetailViewModel by lazy {
        activityScope.getViewModel<PokeDetailViewModel>(requireParentFragment())
    }
    private val pokemonInfos by navArgs<PokeDetailDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        vm.setPokemonInfos(pokemonInfos.pokemonId, pokemonInfos.pokeMapInfos)
        return view
    }

    override fun getLayoutId(): Int = R.layout.pokedetail_fragment

    override fun createViewModel(): BaseViewModel = vm
}