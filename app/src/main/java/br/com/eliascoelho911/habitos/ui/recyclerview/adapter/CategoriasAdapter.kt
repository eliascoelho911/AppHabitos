package br.com.eliascoelho911.habitos.ui.recyclerview.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.databinding.ItemCategoriaBinding
import br.com.eliascoelho911.habitos.model.Categoria
import br.com.eliascoelho911.habitos.util.getDrawable
import kotlinx.android.synthetic.main.item_categoria.view.*
import org.koin.java.KoinJavaComponent.inject

class CategoriasAdapter(
    private val categorias: List<Categoria>
) : ListAdapter<Categoria, CategoriasAdapter.CategoriaViewHolder>(DiffCallback) {
    private val context: Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemCategoriaBinding.inflate(inflater, parent, false)
        return CategoriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.vincula(categorias[position])
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    inner class CategoriaViewHolder(private val binding: ItemCategoriaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var categoria: Categoria
        private val habitosRecyclerView by lazy {
            binding.root.item_categoria_habitos
        }

        fun vincula(categoria: Categoria) {
            this.categoria = categoria
            configuraBinding(categoria)
            configuraAdapterDaListaDeHabitos(categoria)
        }

        private fun configuraBinding(categoria: Categoria) {
            binding.categoria = categoria
            val elementosItemCategoria = ElementosItemCategoria()
            binding.elementos = elementosItemCategoria
            binding.onClick = View.OnClickListener {
                elementosItemCategoria.alterouEstado()
            }
        }

        private fun configuraAdapterDaListaDeHabitos(categoria: Categoria) {
            habitosRecyclerView.adapter = HabitosAdapter(categoria.habitos)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Categoria>() {
        override fun areItemsTheSame(oldItem: Categoria, newItem: Categoria) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Categoria, newItem: Categoria) =
            oldItem == newItem
    }

    class ElementosItemCategoria {
        val estaExpandido = ObservableBoolean(false)
        val background = ObservableField(getBackground())
        val imagemBotao = ObservableField(getImagemBotao())

        private fun getBackground(): Drawable {
            val drawableId = if (estaExpandido.get()) {
                R.drawable.background_item_categoria_expandido
            } else {
                R.drawable.background_item_categoria_contraido
            }

            return getDrawable(drawableId)!!
        }

        private fun getImagemBotao(): Drawable {
            val drawableId = if (estaExpandido.get()) {
                R.drawable.ic_contrair_categoria
            } else {
                R.drawable.ic_expandir_categoria
            }

            return getDrawable(drawableId)!!
        }

        fun alterouEstado() {
            estaExpandido.set(estaExpandido.get().not())
            background.set(getBackground())
            imagemBotao.set(getImagemBotao())
        }
    }
}