package com.swkang.model.domain.pokesearch.datas

data class PokemonsVO(
    val pokemons: List<PokemonNameVO>
)

data class PokemonNameVO(
    val id: Long,
    val names: List<String>
)

data class PokemonName(
    val id: Long,
    val korName: String,
    val engName: String
)

fun PokemonNameVO.toPokemonData(): PokemonName {
    return PokemonName(id, names[0], names[1])
}