package online.jadg13.clase7.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import online.jadg13.clase7.R
import online.jadg13.clase7.entities.City

class CityAdapter(private val context: Context, private var cityList: List<City>): BaseAdapter() {
    override fun getCount(): Int {
        return cityList.size
    }

    override fun getItem(position: Int): Any {
        return cityList[position]
    }

    override fun getItemId(position: Int): Long {
        return cityList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_city, parent, false)
        val city = cityList[position]

        view.findViewById<TextView>(R.id.tvNameCity).text = city.name
        view.findViewById<TextView>(R.id.tvDescriptionCity).text = city.description
        view.findViewById<TextView>(R.id.tvPopulationCity).text = city.population.toString()
        return  view
    }

    fun updateCities(newCityList: List<City>){
        cityList = newCityList
        notifyDataSetChanged()
    }
}