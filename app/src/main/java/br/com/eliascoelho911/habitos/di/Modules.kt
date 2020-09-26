package br.com.eliascoelho911.habitos.di

import android.content.Context
import androidx.core.os.ConfigurationCompat
import org.koin.dsl.module
import java.util.*

val systemModule = module {
    single<Locale> {
        ConfigurationCompat.getLocales(get<Context>().resources.configuration)[0]
    }
}