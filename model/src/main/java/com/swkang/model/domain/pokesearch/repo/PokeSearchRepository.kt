package com.swkang.model.domain.pokesearch.repo

import com.swkang.model.domain.pokesearch.datas.PokemonCoordinate
import com.swkang.model.domain.pokesearch.datas.PokemonName
import io.reactivex.rxjava3.core.Single

interface PokeSearchRepository {

    fun requestPokemonNames(query: String? = null): Single<List<PokemonName>>

    fun requestPokemonLocations(id: Long? = null): Single<List<PokemonCoordinate>>

}