package br.com.eliascoelho911.habitos.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.databinding.FragmentTelaHabitosBinding
import br.com.eliascoelho911.habitos.extensions.formata
import br.com.eliascoelho911.habitos.model.Categoria
import br.com.eliascoelho911.habitos.model.Habito
import br.com.eliascoelho911.habitos.ui.recyclerview.adapter.CategoriasAdapter
import br.com.eliascoelho911.habitos.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_tela_habitos.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TelaHabitosFragment : Fragment() {
    private val mainActivityViewModel: MainActivityViewModel by sharedViewModel()
    private lateinit var binding: FragmentTelaHabitosBinding
    private val categoriasRecyclerView by lazy {
        fragment_item_habitos_categorias
    }

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
        mainActivityViewModel.configuraInterfaceDaActivity(exibirCalendario = true)
        mainActivityViewModel.diaSelecionadoNoCalendario.observe(this, { diaSelecionado ->
            mainActivityViewModel.alteraTitulo(
                diaSelecionado?.formata("dd 'de' MMMMM") ?: getString(R.string.app_name)
            )
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriasAdapter = CategoriasAdapter(
            listOf(
                Categoria(
                    "Saúde", R.color.verde400, listOf(
                        Habito(nome = "Correr", meta = 88),
                        Habito(nome = "Ir para a academia", meta = 88)
                    )
                ),
                Categoria(
                    "Trabalho", R.color.roxo400, listOf(
                        Habito(nome = "Fazer pausar periódicas", meta = 88),
                        Habito(nome = "Lanchar no meio da tarde", meta = 88),
                        Habito(nome = "Sair as 17h", meta = 88)
                    )
                ),
                Categoria(
                    "Escola", R.color.azul400, listOf(
                        Habito(nome = "Anotar todas a matéria", meta = 88),
                        Habito(nome = "Ficar até o final da aula", meta = 88)
                    )
                ),
                Categoria(
                    "Lazer", R.color.vermelho400, listOf(
                        Habito(nome = "Jogar", meta = 88),
                        Habito(nome = "Nadar", meta = 88)
                    )
                )
            )
        )
        categoriasRecyclerView.adapter = categoriasAdapter
    }
}