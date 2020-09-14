package com.myk.playground

import android.app.Application
import com.myk.playground.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@App)
            // logging
            androidLogger()
            // declare modules
            modules(sharedModules)
        }
    }
}
