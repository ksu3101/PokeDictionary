package com.swkang.pokedictionary.base.di

import com.swkang.common.DEFAULT_TIMEOUT_SEC
import com.swkang.common.QUALIFIER_POKEAPI
import com.swkang.common.QUALIFIER_POKESEARCH
import com.swkang.model.base.helpers.MessageHelper
import com.swkang.model.base.helpers.ResourceHelper
import com.swkang.model.domain.pokedetail.PokeDetailViewModel
import com.swkang.model.domain.pokesearch.PokemonNavigationHelper
import com.swkang.model.domain.pokesearch.PokeSearchViewModel
import com.swkang.model.domain.pokesearch.repo.PokeSearchRepository
import com.swkang.model.domain.pokesearch.repo.PokemonRepository
import com.swkang.pokedictionary.base.helpers.MessageHelperImpl
import com.swkang.pokedictionary.base.helpers.ResourceHelperImpl
import com.swkang.pokedictionary.repositories.pokesearch.PokeApi
import com.swkang.pokedictionary.repositories.pokesearch.PokeSearchApi
import com.swkang.pokedictionary.repositories.pokesearch.PokeSearchRepositoryImpl
import com.swkang.pokedictionary.repositories.pokesearch.PokemonRepositoryImpl
import com.swkang.pokedictionary.view.pokesearch.PokemonNavigationHelperImpl
import com.swkang.pokedictionary.view.pokesearch.PokemonSearchActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single<MessageHelper> { MessageHelperImpl(androidContext()) }
    single<ResourceHelper> { ResourceHelperImpl(androidContext()) }
}

val repositories = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single(named(QUALIFIER_POKESEARCH)) {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://demo0928971.mockable.io/")
            .client(get<OkHttpClient>())
            .build()
    }
    single<PokeSearchApi> {
        get<Retrofit>(named(QUALIFIER_POKESEARCH))
            .create(PokeSearchApi::class.java)
    }
    single<PokeSearchRepository> {
        PokeSearchRepositoryImpl(get())
    }

    single(named(QUALIFIER_POKEAPI)) {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(get<OkHttpClient>())
            .build()
    }
    single<PokeApi> {
        get<Retrofit>(named(QUALIFIER_POKEAPI))
            .create(PokeApi::class.java)
    }
    single<PokemonRepository> {
        PokemonRepositoryImpl(get())
    }
}

val activityModules = module {
    scope(named<PokemonSearchActivity>()) {
        scoped<PokemonNavigationHelper> {
            PokemonNavigationHelperImpl(get<PokemonSearchActivity>())
        }
        viewModel { PokeSearchViewModel(get(), get(), get(), get()) }
        viewModel { PokeDetailViewModel(get(), get(), get()) }
    }
}
