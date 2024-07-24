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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val listado = service.listarEstado()


            val strMsn = listado.joinToString(separator = "\n") { it.toString() }
            binding.TvMsn.text = strMsn
            Log.e("MisRegistros", listado.toString())
        }


        setContentView(binding.root)

    }
}