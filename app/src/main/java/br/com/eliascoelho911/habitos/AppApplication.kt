package br.com.eliascoelho911.habitos

import android.app.Application
import br.com.eliascoelho911.habitos.di.systemModule
import br.com.eliascoelho911.habitos.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(listOf(systemModule, uiModule))
        }
    }

}