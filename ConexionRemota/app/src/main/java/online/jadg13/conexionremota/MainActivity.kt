package online.jadg13.conexionremota

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.jadg13.conexionremota.data.APIService
import online.jadg13.conexionremota.data.adpater.EstadoAdapter
import online.jadg13.conexionremota.data.models.Estado
import online.jadg13.conexionremota.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EstadoAdapter
    private val listado = mutableListOf<Estado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        showEstados()
    }

    private fun initRecyclerView() {
        adapter = EstadoAdapter(listado)
        binding.RvEstado.layoutManager = LinearLayoutManager(this)
        binding.RvEstado.adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jadg13.online/crm/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showEstados() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).listarEstado("test/show")
            val response = call.body()
            runOnUiThread {
                if (call.isSuccessful && response != null) {
                    val listaEst = response.estados.toMutableList()
                    listado.clear()
                    listado.addAll(listaEst)
                    Log.e("listado", listaEst.toString())

                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }
}