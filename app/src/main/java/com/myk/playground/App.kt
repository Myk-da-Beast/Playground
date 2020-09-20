package com.myk.playground

import android.app.Application
import coil.ImageLoader
import coil.util.CoilUtils
import com.myk.playground.di.sharedModules
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Timber
        Timber.plant(Timber.DebugTree())

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@App)
            // logging
            androidLogger()
            // declare modules
            modules(sharedModules)
        }

        ImageLoader.Builder(this)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .build()
            }
            .build()
    }
}
