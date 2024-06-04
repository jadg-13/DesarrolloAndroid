package com.example.ejemplo3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejemplo3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.BtnEvaluar.setOnClickListener {
            val num = binding.TfNumber.editText!!.text.toString().toDouble()
            var msn = isPositive(num)
            Toast.makeText(this, msn, Toast.LENGTH_LONG).show()

        }

    }

    fun isPositive(number:Double):String{
        return if (number>0) "El $number es Positivo"
        else if(number<0) "El $number es Negativo"
        else "El $number es Neutro"
    }
}