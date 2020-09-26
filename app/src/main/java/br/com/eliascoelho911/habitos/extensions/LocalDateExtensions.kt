package br.com.eliascoelho911.habitos.extensions

import org.joda.time.LocalDate
import org.koin.java.KoinJavaComponent
import java.util.*

private val locale: Locale by KoinJavaComponent.inject(Locale::class.java)

fun LocalDate.formata(pattern: String) = toString(pattern, locale)