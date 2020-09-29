package br.com.eliascoelho911.habitos.model

import androidx.annotation.ColorRes

data class Categoria(
    val nome: String,
    @ColorRes val cor: Int,
    val habitos: List<Habito>
) {
    fun getColor() = br.com.eliascoelho911.habitos.util.getColor(cor)
}