package com.swkang.model.domain.pokesearch.repo

import com.swkang.model.domain.pokesearch.datas.Pokemon
import io.reactivex.rxjava3.core.Single

interface PokemonRepository {

    fun retrievePokemonInfos(id: Long): Single<Pokemon>

}