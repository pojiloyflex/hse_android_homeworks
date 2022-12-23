package io.github.hse.albumwatcher

import android.app.Application
import io.github.hse.albumwatcher.di.appModule
import io.github.hse.albumwatcher.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class AlbumWatcherApp: Application() {
    override fun onCreate() {
        startKoin {
            modules(viewModelModule, appModule)
        }
        super.onCreate()
    }
}