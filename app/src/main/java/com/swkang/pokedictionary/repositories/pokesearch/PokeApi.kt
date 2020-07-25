package com.swkang.pokedictionary.repositories.pokesearch

import com.swkang.model.domain.pokesearch.datas.Pokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{id}")
    fun retrievePokemonInfos(@Path("id") id: Long): Single<Pokemon>

}