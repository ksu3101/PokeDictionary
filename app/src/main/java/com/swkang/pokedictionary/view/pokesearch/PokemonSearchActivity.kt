package com.swkang.pokedictionary.view.pokesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swkang.common.POKEMON_SEARCH_ACTIVITY_SCOPEID
import com.swkang.pokedictionary.R
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class PokemonSearchActivity : AppCompatActivity() {
    private val activityScope =
        getKoin().getOrCreateScope(POKEMON_SEARCH_ACTIVITY_SCOPEID, named<PokemonSearchActivity>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokesearch_activity)
        activityScope.declare(this)
    }

    override fun onDestroy() {
        activityScope.close()
        super.onDestroy()
    }

}