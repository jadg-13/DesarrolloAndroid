package online.jadg13.conexionremota.data.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import online.jadg13.conexionremota.R
import online.jadg13.conexionremota.data.models.Estado

class EstadoAdapter(val lstEstado: MutableList<Estado>):RecyclerView.Adapter<EstadoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val binding = LayoutInflater.from(parent.context)
        return EstadoViewHolder(binding.inflate(R.layout.estado_item, parent, false))
    }

    override fun getItemCount():Int = lstEstado.size

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val item =lstEstado[position]
        holder.bind(item)
    }
}