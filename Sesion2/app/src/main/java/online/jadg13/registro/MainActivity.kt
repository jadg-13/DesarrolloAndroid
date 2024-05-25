package online.jadg13.registro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import online.jadg13.registro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        start()
    }

    private fun start() {
        binding.BtnShow.setOnClickListener {
            var name = binding.EtName.text.toString()
            Toast.makeText(this, "Hola $name, bienvenido a la UNIVALLE",
                Toast.LENGTH_LONG).show()
        }
    }


}