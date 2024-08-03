package online.jadg13.crmapi.data.adapter

import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import online.jadg13.crmapi.R
import online.jadg13.crmapi.data.models.Data
import online.jadg13.crmapi.databinding.ItemcityBinding

class CityViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemcityBinding.bind(view)
    private val btnEdit = binding.btnEdit
    private val btnDelete = binding.btnDel

    fun bind(city: Data, onEditClick: (Data) -> Unit, onDeleteClick: (Data) -> Unit) {
        binding.tvName.text = city.name
        binding.tvDescription.text = city.description
        Picasso.get()
            .load(city.image)
            .placeholder(R.drawable.baseline_flag_circle_24)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivImage)
        btnEdit.setOnClickListener { onEditClick(city) }
        btnDelete.setOnClickListener { onDeleteClick(city) }

    }


}