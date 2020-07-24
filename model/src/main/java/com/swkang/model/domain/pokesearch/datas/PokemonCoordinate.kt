package com.swkang.model.domain.pokesearch.datas

data class PokemonCoordinateVO(
    val pokemons: List<PokemonCoordinate>
)

data class PokemonCoordinate(
    val id: Long,
    val lat: Double,
    val lng: Double
)