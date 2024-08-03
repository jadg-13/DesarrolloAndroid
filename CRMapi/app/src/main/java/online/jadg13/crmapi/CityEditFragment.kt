package online.jadg13.crmapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.jadg13.crmapi.data.APIService
import online.jadg13.crmapi.data.RetrofitBuilder
import online.jadg13.crmapi.data.models.Data
import online.jadg13.crmapi.databinding.FragmentCityEditBinding


class CityEditFragment : Fragment() {
    private lateinit var binding: FragmentCityEditBinding
    private var cityId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments ?: return
        cityId = arguments.getInt("city_id")
        loadCity(cityId)

        binding.btnUpdate.setOnClickListener {
            updateCity()
        }
    }

    private fun updateCity() {
        CoroutineScope(Dispatchers.IO).launch {
            val city = Data(
                id = cityId,
                name = binding.tfNameEd.editText?.text?.trim().toString(),
                description = binding.tfDescriptionEd.editText?.text?.trim().toString(),
                image = binding.tfURLImageEd.editText?.text?.trim().toString()
            )

            val retrofit = RetrofitBuilder.getRetrofit()
            val call = retrofit.create(APIService::class.java).updateCity("cities", city.id, city)
            val response = call.body()
            requireActivity().runOnUiThread {
                if(call.isSuccessful && response != null){
                    Toast.makeText(context, "Registro actualizado...", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(context, "Error al actualizar", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun loadCity(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val retrofit = RetrofitBuilder.getRetrofit()
                val apiService = retrofit.create(APIService::class.java)
                val response = apiService.showCity("cities", id)
                if (response.isSuccessful) {
                    val city = response.body()
                    if (city != null) {
                        Log.e("Data", "City data received: $city")

                        // Actualiza la UI con los datos de la ciudad en el hilo principal
                        binding.tfNameEd.editText?.setText(city.name)
                        binding.tfDescriptionEd.editText?.setText(city.description)
                        binding.tfURLImageEd.editText?.setText(city.image)
                    } else {
                        Toast.makeText(context, "No data found for the city", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            } catch (ex: Exception) {
                Log.e("Error", "Exception: ${ex.message}")
                Toast.makeText(context, "Error: ${ex.message}", Toast.LENGTH_LONG).show()
            }
        }
    }




}
