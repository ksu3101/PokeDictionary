package com.swkang.model.domain.pokesearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.exts.setFalse
import com.swkang.model.base.helpers.MessageHelper
import com.swkang.model.base.helpers.ResourceHelper
import com.swkang.model.domain.pokesearch.datas.PokemonName
import com.swkang.model.domain.pokesearch.repo.PokeSearchRepository

class PokeSearchViewModel(
    private val repo: PokeSearchRepository,
    private val resourceHelper: ResourceHelper,
    private val messageHelper: MessageHelper,
    private val navigationHelper: PokemonNavigationHelper
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
                .doOnSubscribe { _isLoading.postValue(true) }
                .doFinally { _isLoading.postValue(false) }
                .subscribe(
                    {
                        _pokemons.value = it
                        _isLoading.setFalse()
                    },
                    {
                        Log.e("PokeSearchViewModel", it.message)
                        retryToRequestPokemonNames(query)
                    }
                )
        )
    }

    private fun retryToRequestPokemonNames(query: String?) {
        addDisposer(
            messageHelper.createReTryActionDialog(message = resourceHelper.getString(R.string.c_retry_desc))
                .subscribe {
                    requestPokemonNames(query)
                }
        )
    }

    private fun requestPokemonLocations(id: Long?) {
        addDisposer(
            repo.requestPokemonLocations(id)
                .doOnSubscribe { _isLoading.postValue(true) }
                .doFinally { _isLoading.postValue(false) }
                .subscribe(
                    {
                        // nothing to do
                    },
                    {
                        Log.e("PokeSearchViewModel", it.message)
                        retryToRequestPokemonLocations(id)
                    }
                )
        )
    }

    private fun retryToRequestPokemonLocations(id: Long?) {
        addDisposer(
            messageHelper.createReTryActionDialog(message = resourceHelper.getString(R.string.c_retry_desc))
                .subscribe {
                    requestPokemonLocations(id)
                }
        )
    }

}