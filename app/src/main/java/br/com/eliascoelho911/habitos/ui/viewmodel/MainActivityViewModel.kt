package br.com.eliascoelho911.habitos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val exibirCalendarioHorizontal = MutableLiveData(false)

    fun configuraInterface(exibirCalendarioHorizontal: Boolean) {
        this.exibirCalendarioHorizontal.value = exibirCalendarioHorizontal
    }
}
