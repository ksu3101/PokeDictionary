package com.swkang.model.domain.pokesearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.exts.setFalse
import com.swkang.model.base.exts.setTrue
import com.swkang.model.base.helpers.MessageHelper
import com.swkang.model.domain.pokesearch.datas.PokemonName
import com.swkang.model.domain.pokesearch.repo.PokeSearchRepository

class PokeSearchViewModel(
    private val repo: PokeSearchRepository,
    private val messageHelper: MessageHelper,
    private val navigationHelper: PokeSearchNavigationHelper
) : BaseViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _query = MutableLiveData("")
    val query: LiveData<String>
        get() = _query

    private val _pokemons = MutableLiveData<List<PokemonName>>(listOf())
    val pokemons: LiveData<List<PokemonName>>
        get() = _pokemons

    val onQueryStringChanged: (String) -> Unit = {
        Log.d("PokeSearchViewModel", ">>> onQueryStringChanged : $it")
        requestPokemonNames(it)
    }

    val onPokemonClicked: (PokemonName) -> Unit = {
        Log.d("PokeSearchViewModel", ">>> onPokemonClicked : ${it.id}, ${it.korName}")
    }

    init {
        requestPokemonNames(null)
        requestPokemonLocations(null)
    }

    private fun requestPokemonNames(query: String?) {
        addDisposer(
            repo.requestPokemonNames(query)
//                .doOnSubscribe { _isLoading.setTrue() }
//                .doFinally { _isLoading.setFalse() }
                .subscribe(
                    {
                        _pokemons.value = it
                    },
                    {
                        Log.e("PokeSearchViewModel", it.message)
                    }
                )
        )

    }

    private fun requestPokemonLocations(id: Long?) {
        addDisposer(
            repo.requestPokemonLocations(id)
                .doOnSubscribe { _isLoading.setTrue() }
                .doFinally { _isLoading.setFalse() }
                .subscribe(
                    {
                        // nothing to do
                    },
                    {
                        Log.e("PokeSearchViewModel", it.message)
                    }
                )
        )
    }

}