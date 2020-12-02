package com.yvkalume.dcplus.app

import android.app.Application
import com.yvkalume.dcplus.app.di.interactorModule
import com.yvkalume.dcplus.app.di.presenterModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin

class App : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(interactorModule, presenterModule)
        }
    }
}