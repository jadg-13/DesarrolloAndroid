package online.jadg13.conexionremota.data.adpater

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import online.jadg13.conexionremota.data.models.Estado
import online.jadg13.conexionremota.databinding.EstadoItemBinding

class EstadoViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = EstadoItemBinding.bind(view)


    fun bind(estado: Estado){
        binding.TvId.text = estado.id.toString()
        binding.TvName.text = estado.name
        binding.TvDescription.text =estado.description
    }
}