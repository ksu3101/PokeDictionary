package com.swkang.pokedictionary.repositories.pokesearch

import com.swkang.model.domain.pokesearch.datas.Pokemon
import com.swkang.model.domain.pokesearch.repo.PokemonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PokemonRepositoryImpl(
    private val api: PokeApi
) : PokemonRepository {

    override fun retrievePokemonInfos(id: Long): Single<Pokemon> {
        return api.retrievePokemonInfos(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}