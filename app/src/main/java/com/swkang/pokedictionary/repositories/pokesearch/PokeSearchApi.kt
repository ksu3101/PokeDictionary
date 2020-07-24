package com.swkang.pokedictionary.repositories.pokesearch

import com.swkang.model.domain.pokesearch.datas.PokemonCoordinateVO
import com.swkang.model.domain.pokesearch.datas.PokemonsVO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PokeSearchApi {

    @GET("pokemon_name")
    fun requestPokemonNames(): Single<PokemonsVO>

    @GET("pokemon_locations")
    fun requestPokemonLocations(): Single<PokemonCoordinateVO>

}