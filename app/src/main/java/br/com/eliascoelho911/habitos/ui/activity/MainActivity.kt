package br.com.eliascoelho911.habitos.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import br.com.eliascoelho911.habitos.databinding.ActivityMainBinding
import br.com.eliascoelho911.habitos.ui.recyclerview.adapter.CalendarioHorizontalAdapter
import br.com.eliascoelho911.habitos.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = criaBinding()
        setContentView(binding.root)
        setSupportActionBar(activity_main_toolbar)

        activity_main_calendario.adapter = CalendarioHorizontalAdapter()
    }

    private fun criaBinding() = ActivityMainBinding.inflate(LayoutInflater.from(this)).apply {
        viewModel = this@MainActivity.viewModel
    }

}