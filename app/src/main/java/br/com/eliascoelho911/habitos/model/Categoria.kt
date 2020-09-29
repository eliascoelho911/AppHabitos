package br.com.eliascoelho911.habitos.model

import androidx.annotation.ColorRes

data class Categoria(
    val nome: String,
    @ColorRes val cor: Int,
    val todosHabitos: List<Habito>
) {
    var habitosDisponiveis = todosHabitos
        private set

    fun getColor() = br.com.eliascoelho911.habitos.util.getColor(cor)

    fun filtrarHabitos(dia: Int) {
        habitosDisponiveis = todosHabitos.filter { it.dias.tem(dia) }
    }

    fun ehValida(): Boolean {
        return habitosDisponiveis.isNotEmpty()
    }
}