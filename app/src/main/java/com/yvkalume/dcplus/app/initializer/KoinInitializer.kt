package com.yvkalume.dcplus.app.initializer


import android.content.Context
import androidx.startup.Initializer
import com.yvkalume.dcplus.app.di.firebaseModule
import com.yvkalume.dcplus.app.di.interactorModule
import com.yvkalume.dcplus.app.di.presenterModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<Unit> {
    @ExperimentalCoroutinesApi
    override fun create(context: Context) {
        startKoin {
            modules(interactorModule,presenterModule,firebaseModule)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
