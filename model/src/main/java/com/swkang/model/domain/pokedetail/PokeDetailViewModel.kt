package com.swkang.model.domain.pokedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helpers.ResourceHelper
import com.swkang.model.domain.pokesearch.PokemonNavigationHelper
import com.swkang.model.domain.pokesearch.datas.Pokemon
import com.swkang.model.domain.pokesearch.datas.PokemonMapCoordinates
import com.swkang.model.domain.pokesearch.repo.PokemonRepository
import io.reactivex.rxjava3.disposables.Disposable

class PokeDetailViewModel(
    private val repo: PokemonRepository,
    private val resourceHelper: ResourceHelper,
    private val navigationHelper: PokemonNavigationHelper
) : BaseViewModel() {
    private lateinit var pokemonLocations: Array<PokemonMapCoordinates>
    private lateinit var retrieveJobDisposer: Disposable

    private val _pokemonName = MutableLiveData("")
    val pokemonName: LiveData<String>
        get() = _pokemonName

    private val _pokemonHeight = MutableLiveData("")
    val pokemonHeight: LiveData<String>
        get() = _pokemonHeight

    private val _pokemonWeight = MutableLiveData("")
    val pokemonWeight: LiveData<String>
        get() = _pokemonWeight

    private val _pokemonSpriteUrl = MutableLiveData<String?>(null)
    val pokemonSprite: LiveData<String?>
        get() = _pokemonSpriteUrl

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _hasPokemonLocations = MutableLiveData(false)
    val hasPokemonLocations: LiveData<Boolean>
        get() = _hasPokemonLocations

    /**
     * 확인 버튼 터치 이벤트 핸들러
     */
    val onClickedConfirmBtn = {
        if (::retrieveJobDisposer.isInitialized && !retrieveJobDisposer.isDisposed) {
            retrieveJobDisposer.dispose()
        }
        navigationHelper.dismissPokemonDetailPopup()
    }

    /**
     * 포켓몬 위치가 있을 경우 -> "지도 보기" 버튼 보여짐 -> 터치 이벤트 핸들러
     */
    val onClickedShowPokemonLocationsBtn = {
        navigationHelper.openMapOfPokemonLocations(pokemonLocations)
        navigationHelper.dismissPokemonDetailPopup()
    }

    fun setPokemonInfos(pokemonId: Long, coordinates: Array<PokemonMapCoordinates>) {
        pokemonLocations = coordinates
        requestDetailPokemonInfos(pokemonId)
    }

    private fun requestDetailPokemonInfos(id: Long) {
        retrieveJobDisposer = repo.retrievePokemonInfos(id)
            .doOnSubscribe { _isLoading.postValue(true) }
            .doFinally { _isLoading.postValue(false) }
            .subscribe({
                updateViewsByPokemonInfos(it)
            }, {
                Log.e("PokeDetailViewModel", ">>> ${it.message}")
            })
        addDisposer(retrieveJobDisposer)
    }

    private fun updateViewsByPokemonInfos(pokemon: Pokemon) {
        _hasPokemonLocations.value = pokemonLocations.isNotEmpty()
        _pokemonName.value = pokemon.name
        _pokemonHeight.value =
            resourceHelper.getString(R.string.pokemon_detail_height, pokemon.height)
        _pokemonWeight.value =
            resourceHelper.getString(R.string.pokemon_detail_weight, pokemon.weight)
        _pokemonSpriteUrl.value = pokemon.getCorrectPokemonSpriteUrl()
    }

}