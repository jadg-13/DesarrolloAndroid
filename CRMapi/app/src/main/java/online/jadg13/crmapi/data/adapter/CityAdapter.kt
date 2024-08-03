package online.jadg13.crmapi.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import online.jadg13.crmapi.R
import online.jadg13.crmapi.data.models.Data

class CityAdapter(val lstCities: MutableList<Data>,
                  private val onEditClick: (Data) -> Unit,
                  private val onDeleteClick: (Data) -> Unit): RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = LayoutInflater.from(parent.context)
        return CityViewHolder(binding.inflate(R.layout.itemcity, parent, false))
    }

    override fun getItemCount(): Int = lstCities.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = lstCities[position]
        holder.bind(item, onEditClick, onDeleteClick)
    }
}