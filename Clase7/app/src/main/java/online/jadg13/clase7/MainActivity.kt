package online.jadg13.clase7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.runBlocking
import online.jadg13.clase7.dao.DatabaseBuilder
import online.jadg13.clase7.databinding.ActivityMainBinding
import online.jadg13.clase7.entities.City

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        funMain()

    }

    private fun funMain() = runBlocking{
        val context = this@MainActivity
        val db = DatabaseBuilder.getInstance(context)

        val city1 = City(0, "Masaya", "Ciudad de las flores", 50000)
        val city2 = City(0, "Managua", "Capital de Nicaragua", 500600)
        val city3 = City(0, "Granada", "La gran sultana", 200600)

        var listCities : List<City> = listOf(city1, city2, city3)

        val cityDao = db.cityDao()

        listCities.forEach{ city ->
            cityDao.insert(city)
        }

    }
}