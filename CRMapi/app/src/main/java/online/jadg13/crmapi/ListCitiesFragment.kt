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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import online.jadg13.crmapi.data.APIService
import online.jadg13.crmapi.data.RetrofitBuilder
import online.jadg13.crmapi.data.adapter.CityAdapter
import online.jadg13.crmapi.data.models.Data
import online.jadg13.crmapi.databinding.FragmentListCitiesBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListCitiesFragment : Fragment() {
    private lateinit var binding: FragmentListCitiesBinding
    private lateinit var adapter: CityAdapter
    private val listado = mutableListOf<Data>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListCitiesBinding.inflate(inflater, container, false)

        initView()
        initRecyclerView()
        showCities()

        return binding.root
    }

    private fun initView() {
        binding.btnAdd.setOnClickListener {
            goNewCity()
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            searchCities(query)
        }
    }

    private fun initRecyclerView() {
        adapter = CityAdapter(listado, this::onEditClick, this::onDeleteClick)

        binding.RvLstCities.layoutManager = LinearLayoutManager(context)
        binding.RvLstCities.adapter = adapter
    }

    private fun onDeleteClick(data: Data) {
        val context = requireContext()

        lifecycleScope.launch {
            try {
                val retrofit = RetrofitBuilder.getRetrofit()
                val call = retrofit.create(APIService::class.java).deleteCity("cities", data.id)
                val response = call.body()
                requireActivity().runOnUiThread {
                    if (call.isSuccessful) {
                        listado.remove(data)
                        adapter.notifyDataSetChanged()
                    } else Toast.makeText(context, "Error al eliminar", Toast.LENGTH_LONG).show()
                    Toast.makeText(context, "Registro eliminado...", Toast.LENGTH_LONG).show()
                }
            } catch (ex: Exception) {
                Log.e("errojd", ex.toString())
                Toast.makeText(context, "erro ${ex.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onEditClick(data: Data) {
        val bundle = Bundle()
        bundle.putInt("city_id", data.id)
        findNavController().navigate(R.id.action_listCitiesFragment_to_cityEditFragment, bundle)
    }

    private fun showCities() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = RetrofitBuilder.getRetrofit()
            val call = retrofit.create(APIService::class.java).listCities("cities")
            val response = call.body()
            requireActivity().runOnUiThread {
                if (call.isSuccessful && response != null) {
                    val listaCities = response.data.toMutableList()
                    listado.clear()
                    listado.addAll(listaCities)
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("listaciu", "Hubo un error")
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
    }

    private fun goNewCity() {
        findNavController().navigate(R.id.action_listCitiesFragment_to_cityFragment)
    }

    private fun searchCities(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val retrofit = RetrofitBuilder.getRetrofit()
                val call = retrofit.create(APIService::class.java).listCities("cities", query)
                val response = call.body()
                requireActivity().runOnUiThread {
                    if (call.isSuccessful && response != null) {
                        Log.e("listaciu", "ok")
                        val listaCities = response.data.toMutableList()
                        listado.clear()
                        listado.addAll(listaCities)
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.e("listaciu", "Hubo un error")
                        showError()
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(context, "Failure: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
