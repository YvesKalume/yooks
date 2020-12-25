package com.yvkalume.yooks.app.initializer


import android.content.Context
import androidx.startup.Initializer
import com.yvkalume.yooks.app.di.firebaseModule
import com.yvkalume.yooks.app.di.interactorModule
import com.yvkalume.yooks.app.di.presenterModule
import com.yvkalume.yooks.app.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<Unit> {
    @ExperimentalCoroutinesApi
    override fun create(context: Context) {
        startKoin {
            modules(viewModelModule,interactorModule,presenterModule,firebaseModule)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
