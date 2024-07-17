package online.jadg13.clase7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.combineTransform
import online.jadg13.clase7.adapter.CityViewModel
import online.jadg13.clase7.adapter.CityViewModelFactory
import online.jadg13.clase7.dao.DatabaseBuilder
import online.jadg13.clase7.databinding.FragmentEditCityBinding
import online.jadg13.clase7.entities.City


class EditCityFragment : Fragment() {

    private lateinit var binding: FragmentEditCityBinding

    private val cityViewModel: CityViewModel by viewModels {
        CityViewModelFactory(DatabaseBuilder.getInstance(requireContext()).cityDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditCityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments ?: return
        var cityId = arguments.getInt("city_id")

        showData(cityId)

        binding.btnSave.setOnClickListener {
            val updateCity = City(
                id= binding.tvIdCity.text.toString().toInt(),
                name = binding.tfName.editText?.text.toString(),
                description = binding.tfDescription.editText?.text.toString(),
                population = binding.tfPopulation.editText?.text.toString().toInt()
            )

            cityViewModel.updateCity(updateCity)
            Toast.makeText(context, "Registro actualizado", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }
    }

    private fun showData(cityId: Int) {
        cityViewModel.getCity(cityId).observe(viewLifecycleOwner, Observer {
            city-> city?.let {
                binding.tvIdCity.text = it.id.toString()
                binding.tfName.editText?.setText(it.name)
                binding.tfDescription.editText?.setText(it.description)
                binding.tfPopulation.editText?.setText(it.population.toString())
            }
        })
    }

}