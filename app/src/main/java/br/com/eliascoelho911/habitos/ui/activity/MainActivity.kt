package br.com.eliascoelho911.habitos.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
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
    private val calendarioRadioGroup: RadioGroup by lazy {
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
        viewModel.exibirCalendario.observe(this, {
            if (it == true) {
                mostraCalendario()
                criaEAdicionaDiasNoCalendario()
                val idRadioButton = (viewModel.idRadioButtonSelecionadoNoCalendario
                    ?: calendarioRadioGroup.getChildAt(calendarioRadioGroup.childCount - 1).id)
                selecionaDiaDoCalendario(idRadioButton)
            } else {
                escondeCalendario()
            }
        })
    }

    private fun escondeCalendario() {
        calendarioLayout.visibility = View.GONE
    }

    private fun mostraCalendario() {
        calendarioLayout.visibility = View.VISIBLE
    }

    private fun selecionaDiaDoCalendario(id: Int) {
        calendarioRadioGroup.check(id)
        viewModel.atualizaDiaSelecionadoNoCalendario(dias[id], id)
    }

    private fun criaEAdicionaDiasNoCalendario() {
        dias.forEachIndexed { index, dia ->
            val binding = inflaItemCalendario(calendarioRadioGroup, index)
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
            viewModel.atualizaDiaSelecionadoNoCalendario(dia, it.id)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (viewModel.exibirCalendario.value == true) {
            val idRadioButtonSelecionado = (viewModel.idRadioButtonSelecionadoNoCalendario
                ?: calendarioRadioGroup.childCount - 1)
            moveScrollPara(idRadioButtonSelecionado)
        }
    }

    private fun moveScrollPara(index: Int) {
        val radioButtonSelecionado =
            calendarioRadioGroup.getChildAt(index)
        calendarioLayout.scrollTo(radioButtonSelecionado.left, radioButtonSelecionado.top)
    }

    private fun inflaItemCalendario(parent: ViewGroup, id: Int): ItemCalendarioHorizontalBinding {
        val inflater = LayoutInflater.from(this)
        val binding = ItemCalendarioHorizontalBinding.inflate(inflater, parent, false)
        binding.root.item_calendario_horizontal_botao.id = id

        return binding
    }
}