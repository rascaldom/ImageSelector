package com.kakaobank.imageselector

import android.app.Application
import com.kakaobank.imageselector.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ImageSelectorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ImageSelectorApplication)
            androidLogger(Level.ERROR)
            loadKoinModules(listOf(
                appModule,
                viewModelModule,
                networkModule,
                remoteModule,
                repositoryModule,
                useCaseModule
            ))
        }
    }

}