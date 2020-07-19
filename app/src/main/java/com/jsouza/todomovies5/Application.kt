package com.jsouza.todomovies5

import android.app.Application
import com.jsouza.moviedetail.di.dataModule
import com.jsouza.moviedetail.di.detailModule
import com.jsouza.moviedetail.di.serviceModule
import com.jsouza.moviedetail.di.similarMoviesModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            logger(koinLogger())
            modules(
                modules = listOf(
                    detailModule,
                    similarMoviesModule,
                    serviceModule,
                    dataModule
                )
            )
        }
    }

    private fun koinLogger(): Logger = if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
