package br.com.eliascoelho911.habitos.extensions

import org.joda.time.LocalDate
import org.koin.java.KoinJavaComponent
import java.util.*
import kotlin.math.abs

private val locale: Locale by KoinJavaComponent.inject(Locale::class.java)

fun LocalDate.formata(pattern: String) = toString(pattern, locale)
fun LocalDate.criaIntervaloDeDias(delta: Int): List<LocalDate> {
    return IntRange(0, abs(delta)).map {
        if (delta > 0) {
            plusDays(abs(delta) - it)
        } else {
            minusDays(abs(delta) - it)
        }
    }
}