package online.jadg13.conexionremota

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import online.jadg13.conexionremota.data.RetrofitServiceFactory
import online.jadg13.conexionremota.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val service = RetrofitServiceFactory.makeRetrofitService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        showData()
        setContentView(binding.root)
    }

    fun showData() {
        lifecycleScope.launch {
            try {
                val listado = service.listarEstado()
                val strMsn = listado.joinToString(separator = "\n") { "Name: ${it.name} - Description: ${it.description}" }
                binding.TvMsn.text = strMsn
                Log.e("MisRegistros", listado.toString())
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener los registros", e)
            }
        }
    }

}