package online.jadg13.crmapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import java.util.Date
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import online.jadg13.crmapi.data.APIService
import online.jadg13.crmapi.data.RetrofitBuilder
import online.jadg13.crmapi.data.models.Data
import online.jadg13.crmapi.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityBinding.inflate(layoutInflater)
        initCity()
        return binding.root
    }

    private fun initCity() {
        binding.btnSave.setOnClickListener {
            saveCity()
        }
    }

    private fun saveCity() {
        val context = requireContext()
        CoroutineScope(Dispatchers.IO).launch {
            val city = Data(
                id = 0,
                name = binding.tfName.editText?.text?.trim().toString(),
                description = binding.tfDescription.editText?.text?.trim().toString(),
                image = binding.tfURLImage.editText?.text?.trim().toString(),
                created_at = Date().toString(),
                updated_at = Date().toString()
            )
            val retrofit = RetrofitBuilder.getRetrofit()
            val call = retrofit.create(APIService::class.java).saveCity("cities", city)
            val response = call.body()
            requireActivity().runOnUiThread {
                if (call.isSuccessful && response != null) {
                    Toast.makeText(context, "Registro guardado...", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "Error al guardar...", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}