package br.com.eliascoelho911.habitos.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.eliascoelho911.habitos.databinding.ItemCalendarioHorizontalBinding
import br.com.eliascoelho911.habitos.extensions.formata
import br.com.eliascoelho911.habitos.ui.recyclerview.adapter.CalendarioHorizontalAdapter.DiaViewHolder
import org.joda.time.LocalDate
import org.koin.java.KoinJavaComponent.inject

class CalendarioHorizontalAdapter :
    ListAdapter<LocalDate, DiaViewHolder>(DiffCallback) {

    private val context: Context by inject(Context::class.java)
    private val dias = criaIntervaloDeDias()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemCalendarioHorizontalBinding.inflate(inflater, parent, false)
        return DiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaViewHolder, position: Int) {
        holder.vincula(dias[position])
    }

    override fun getItemCount(): Int {
        return dias.size
    }

    private fun criaIntervaloDeDias(): List<LocalDate> {
        val hoje = LocalDate.now()
        return IntRange(1, 14).map {
            hoje.minusDays(14 - it)
        }
    }

    class DiaViewHolder(private val binding: ItemCalendarioHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun vincula(dia: LocalDate) {
            binding.dia = dia.formata("dd\nMMM")
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<LocalDate>() {
        override fun areItemsTheSame(oldItem: LocalDate, newItem: LocalDate) = oldItem == newItem

        override fun areContentsTheSame(oldItem: LocalDate, newItem: LocalDate) = oldItem == newItem
    }

}

