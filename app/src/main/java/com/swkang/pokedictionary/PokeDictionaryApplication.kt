package com.swkang.pokedictionary

import androidx.multidex.MultiDexApplication
import com.swkang.pokedictionary.base.di.activityModules
import com.swkang.pokedictionary.base.di.appModule
import com.swkang.pokedictionary.base.di.repositories
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokeDictionaryApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokeDictionaryApplication)
            logger(AndroidLogger(Level.DEBUG))

            modules(appModule)
            modules(repositories)
            modules(activityModules)
        }
    }
}