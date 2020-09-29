package br.com.eliascoelho911.habitos.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.eliascoelho911.habitos.databinding.ItemHabitoBinding
import br.com.eliascoelho911.habitos.model.Habito
import org.koin.java.KoinJavaComponent.inject

class HabitosAdapter(
    private val habitos: List<Habito>
) : ListAdapter<Habito, HabitosAdapter.HabitoViewHolder>(DiffUtil) {

    private val context: Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitoViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHabitoBinding.inflate(inflater, parent, false)

        return HabitoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitoViewHolder, position: Int) {
        holder.vincula(habitos[position])
    }

    override fun getItemCount() = habitos.size

    class HabitoViewHolder(private val binding: ItemHabitoBinding) : ViewHolder(binding.root) {
        fun vincula(habito: Habito) {
            binding.habito = habito
        }
    }

    object DiffUtil : ItemCallback<Habito>() {
        override fun areItemsTheSame(oldItem: Habito, newItem: Habito) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Habito, newItem: Habito) = oldItem == newItem
    }

}