package com.swkang.pokedictionary.repositories.pokesearch

import com.swkang.common.exts.isOnlyEnglish
import com.swkang.model.domain.pokesearch.datas.PokemonCoordinate
import com.swkang.model.domain.pokesearch.datas.PokemonName
import com.swkang.model.domain.pokesearch.datas.toPokemonData
import com.swkang.model.domain.pokesearch.repo.PokeSearchRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PokeSearchRepositoryImpl(
    private val api: PokeSearchApi
) : PokeSearchRepository {
    private lateinit var pokemonNames: List<PokemonName>
    private lateinit var pokemonLocations: List<PokemonCoordinate>

    override fun requestPokemonNames(query: String?): Single<List<PokemonName>> {
        return if (!::pokemonNames.isInitialized) {
            api.requestPokemonNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    val pokemons = it.pokemons.map { it.toPokemonData() }
                    pokemonNames = pokemons
                    return@map pokemons
                }
        } else {
            if (query.isNullOrEmpty()) {
                Single.just(pokemonNames)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            } else {
                val isOnlyEngQuery = query.isOnlyEnglish()
                Observable.fromIterable(pokemonNames)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter {
                        val pokemonName = if (isOnlyEngQuery) it.engName else it.korName
                        pokemonName.contains(query, true)
                    }.toList()
            }
        }
    }

    override fun requestPokemonLocations(id: Long?): Single<List<PokemonCoordinate>> {
        return if (!::pokemonLocations.isInitialized) {
            api.requestPokemonLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    pokemonLocations = it.pokemons
                    return@map it.pokemons
                }
        } else {
            if (id == null) {
                Single.error(IllegalArgumentException("pokemon id has not available"))
            } else {
                Observable.fromIterable(pokemonLocations)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .filter { it.id == id }
                    .toList()
            }
        }
    }

}