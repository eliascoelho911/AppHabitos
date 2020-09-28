package br.com.eliascoelho911.habitos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.util.getString
import org.joda.time.LocalDate

class MainActivityViewModel : ViewModel() {
    val exibirCalendarioHorizontal = MutableLiveData(false)
    val diaSelecionadoNoCalendarioHorizontal: MutableLiveData<LocalDate?> = MutableLiveData(null)
    val titulo = MutableLiveData(getString(R.string.app_name))

    fun configuraInterfaceDaActivity(
        exibirCalendarioHorizontal: Boolean = false,
        titulo: String = getString(R.string.app_name)
    ) {
        this.exibirCalendarioHorizontal.value = exibirCalendarioHorizontal
        this.titulo.value = titulo
    }

    fun atualizaDiaSelecionadoNoCalendarioHorizontal(dia: LocalDate) {
        diaSelecionadoNoCalendarioHorizontal.value = dia
    }

    fun alteraTitulo(titulo: String) {
        this.titulo.value = titulo
    }
}
