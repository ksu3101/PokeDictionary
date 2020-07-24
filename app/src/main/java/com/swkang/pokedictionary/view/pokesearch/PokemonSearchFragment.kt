package com.swkang.pokedictionary.view.pokesearch

import com.swkang.common.POKEMON_SEARCH_ACTIVITY_SCOPEID
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.pokesearch.PokeSearchViewModel
import com.swkang.pokedictionary.R
import com.swkang.pokedictionary.base.BaseFragment
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.scope.getViewModel

class PokemonSearchFragment : BaseFragment() {
    private val activityScope = getKoin().getScope(POKEMON_SEARCH_ACTIVITY_SCOPEID)
    private val vm: PokeSearchViewModel by lazy {
        activityScope.getViewModel<PokeSearchViewModel>(requireParentFragment())
    }

    override fun getLayoutId(): Int = R.layout.pokesearch_fragment

    override fun createViewModel(): BaseViewModel = vm
}