package br.com.eliascoelho911.habitos.model

import org.joda.time.Days
import org.joda.time.LocalDate

data class Habito(
    val nome: String,
    val dataCriacao: LocalDate = LocalDate.now(),
    val meta: Int
) {
    fun progressoDaMeta(): String {
        val hoje = LocalDate.now()
        val diasRestantes = meta - Days.daysBetween(dataCriacao, hoje).days

        return "Faltam $diasRestantes dias para fixar o hábito"
    }
}