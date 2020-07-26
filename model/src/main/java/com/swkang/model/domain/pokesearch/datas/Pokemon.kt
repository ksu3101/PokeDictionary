package com.swkang.model.domain.pokesearch.datas

import com.squareup.moshi.Json

// a. 이름 / 대표 이미지 / 키 / 몸무게 / 알려진 서식지 여부
data class Pokemon(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites
) {
    fun getCorrectPokemonSpriteUrl(): String? {
        for (sprite in sprites.toList()) {
            if (!sprite.isNullOrEmpty()) return sprite
            else continue
        }
        return null
    }
}

data class PokemonSprites(
    @field:Json(name = "front_default") val frontDefault: String?,
    @field:Json(name = "front_shiny") val frontShiny: String?,
    @field:Json(name = "front_female") val frontFemale: String?,
    @field:Json(name = "front_shiny_female") val frontShinyFemale: String?,
    @field:Json(name = "back_default") val backDefault: String?,
    @field:Json(name = "back_shiny") val backShiny: String?,
    @field:Json(name = "back_female") val backFemale: String?,
    @field:Json(name = "back_shiny_female") val backShinyFemale: String?
) {
    fun toList(): List<String?> {
        return listOf(
            frontDefault,
            frontShiny,
            frontFemale,
            frontShinyFemale,
            backDefault,
            backShiny,
            backFemale,
            backShinyFemale
        )
    }
}