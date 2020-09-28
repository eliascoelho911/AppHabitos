package br.com.eliascoelho911.habitos.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.databinding.ItemCalendarioHorizontalBinding
import br.com.eliascoelho911.habitos.extensions.criaIntervaloDeDias
import br.com.eliascoelho911.habitos.extensions.formata
import br.com.eliascoelho911.habitos.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_calendario_horizontal.view.*
import org.joda.time.LocalDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()
    private val dias = LocalDate.now().criaIntervaloDeDias(-13)
    private val calendarioRadioGroup by lazy {
        activity_main_calendario_group
    }
    private val calendarioLayout by lazy {
        activity_main_calendario_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar)
        configuraTitulo()
        configuraCalendario()
    }

    private fun configuraTitulo() {
        viewModel.titulo.observe(this, { titulo ->
            title = titulo
        })
    }

    private fun configuraCalendario() {
        viewModel.exibirCalendarioHorizontal.observe(this, {
            if (it == true) {
                mostraCalendario()
                if (calendarioAindaNaoFoiCriado()) {
                    criaEAdicionaDiasNoCalendario()
                    selecionaUltimoDiaDoCalendario()
                    moveScrollParaODiaSelecionadoNoCalendario()
                }
            } else {
                escondeCalendario()
            }
        })
    }

    private fun calendarioAindaNaoFoiCriado() = calendarioRadioGroup.childCount == 0

    private fun escondeCalendario() {
        calendarioLayout.visibility = View.GONE
    }

    private fun mostraCalendario() {
        calendarioLayout.visibility = View.VISIBLE
    }

    private fun selecionaUltimoDiaDoCalendario() {
        calendarioRadioGroup.check(calendarioRadioGroup.childCount)
        viewModel.atualizaDiaSelecionadoNoCalendarioHorizontal(dias.last())
    }

    private fun criaEAdicionaDiasNoCalendario() {
        dias.forEach { dia ->
            val binding = inflaItemCalendario(calendarioRadioGroup)
            binding.dia = dia.formata("dd\nMMM")
            atualizaTituloAoClicarNoDia(binding, dia)
            calendarioRadioGroup.addView(binding.root)
        }
    }

    private fun atualizaTituloAoClicarNoDia(
        binding: ItemCalendarioHorizontalBinding,
        dia: LocalDate
    ) {
        binding.onClick = View.OnClickListener {
            viewModel.atualizaDiaSelecionadoNoCalendarioHorizontal(dia)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (viewModel.exibirCalendarioHorizontal.value == true)
            moveScrollParaODiaSelecionadoNoCalendario()
    }

    private fun moveScrollParaODiaSelecionadoNoCalendario() {
        val radioButtonSelecionado =
            calendarioRadioGroup.getChildAt(calendarioRadioGroup.childCount - 1)
        calendarioLayout.scrollTo(radioButtonSelecionado.left, radioButtonSelecionado.top)
    }

    private fun inflaItemCalendario(parent: ViewGroup): ItemCalendarioHorizontalBinding {
        val inflater = LayoutInflater.from(this)
        val binding = ItemCalendarioHorizontalBinding.inflate(inflater, parent, false)
        binding.root.item_calendario_horizontal_botao.id = View.generateViewId()

        return binding
    }
}