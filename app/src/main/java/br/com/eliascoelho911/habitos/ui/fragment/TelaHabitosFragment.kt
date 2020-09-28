package br.com.eliascoelho911.habitos.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.databinding.FragmentTelaHabitosBinding
import br.com.eliascoelho911.habitos.extensions.formata
import br.com.eliascoelho911.habitos.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TelaHabitosFragment : Fragment() {
    private val mainActivityViewModel: MainActivityViewModel by sharedViewModel()
    private lateinit var binding: FragmentTelaHabitosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTelaHabitosBinding.inflate(inflater, container, false)
        binding.saudacao = "Bem vindo, Elias!"
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.configuraInterfaceDaActivity(exibirCalendarioHorizontal = true)
        mainActivityViewModel.diaSelecionadoNoCalendarioHorizontal.observe(this, { diaSelecionado ->
            mainActivityViewModel.alteraTitulo(
                diaSelecionado?.formata("dd 'de' MMMMM") ?: getString(R.string.app_name)
            )
        })
    }

}