package br.com.eliascoelho911.habitos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.util.getString
import org.joda.time.LocalDate

class MainActivityViewModel : ViewModel() {
    val exibirCalendario = MutableLiveData(false)
    val diaSelecionadoNoCalendario: MutableLiveData<LocalDate?> = MutableLiveData(null)
    var idRadioButtonSelecionadoNoCalendario: Int? = null
        private set
    val titulo = MutableLiveData(getString(R.string.app_name))

    fun configuraInterfaceDaActivity(
        exibirCalendario: Boolean = false,
        titulo: String = getString(R.string.app_name)
    ) {
        this.exibirCalendario.value = exibirCalendario
        this.titulo.value = titulo
    }

    fun atualizaDiaSelecionadoNoCalendario(dia: LocalDate, idRadioButton: Int) {
        diaSelecionadoNoCalendario.value = dia
        idRadioButtonSelecionadoNoCalendario = idRadioButton
    }

    fun alteraTitulo(titulo: String) {
        this.titulo.value = titulo
    }
}
