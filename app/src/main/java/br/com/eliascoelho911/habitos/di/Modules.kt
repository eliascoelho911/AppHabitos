package br.com.eliascoelho911.habitos.di

import android.content.Context
import androidx.core.os.ConfigurationCompat
import br.com.eliascoelho911.habitos.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val systemModule = module {
    single<Locale> {
        ConfigurationCompat.getLocales(get<Context>().resources.configuration)[0]
    }
}

val uiModule = module {
    viewModel<MainActivityViewModel> {
        MainActivityViewModel()
    }
}