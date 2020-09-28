package br.com.eliascoelho911.habitos.ui.recyclerview.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.eliascoelho911.habitos.R
import br.com.eliascoelho911.habitos.databinding.ItemCategoriaBinding
import br.com.eliascoelho911.habitos.model.Categoria
import br.com.eliascoelho911.habitos.util.getDrawable
import org.koin.java.KoinJavaComponent.inject

class CategoriasAdapter(private val categorias: List<Categoria>) :
    ListAdapter<Categoria, CategoriasAdapter.CategoriaViewHolder>(DiffCallback) {
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

    class CategoriaViewHolder(private val binding: ItemCategoriaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun vincula(categoria: Categoria) {
            binding.categoria = categoria
            val elementosItemCategoria = ElementosItemCategoria()
            binding.elementos = elementosItemCategoria
            binding.onClickBotaoAlterarEstado = View.OnClickListener {
                elementosItemCategoria.alterouEstado()
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Categoria>() {
        override fun areItemsTheSame(oldItem: Categoria, newItem: Categoria) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Categoria, newItem: Categoria) =
            oldItem == newItem
    }

    class ElementosItemCategoria {
        private var estaExpandido = false
        val background = ObservableField(getBackground())
        val imagemBotao = ObservableField(getImagemBotao())

        private fun getBackground(): Drawable {
            val drawableId = if (estaExpandido) {
                R.drawable.background_item_categoria_expandido
            } else {
                R.drawable.background_item_categoria_contraido
            }

            return getDrawable(drawableId)!!
        }

        private fun getImagemBotao(): Drawable {
            val drawableId = if (estaExpandido) {
                R.drawable.ic_contrair_categoria
            } else {
                R.drawable.ic_expandir_categoria
            }

            return getDrawable(drawableId)!!
        }

        fun alterouEstado() {
            this.estaExpandido = estaExpandido.not()
            background.set(getBackground())
            imagemBotao.set(getImagemBotao())
        }
    }
}