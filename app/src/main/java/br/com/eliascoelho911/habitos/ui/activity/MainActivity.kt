package br.com.eliascoelho911.habitos.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.ui.recyclerview.adapter.CalendarioHorizontalAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar)

        activity_main_calendario.adapter = CalendarioHorizontalAdapter()
    }

}