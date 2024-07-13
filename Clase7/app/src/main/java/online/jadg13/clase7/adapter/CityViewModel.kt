package online.jadg13.clase7.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import online.jadg13.clase7.dao.CityDao
import online.jadg13.clase7.entities.City

class CityViewModel(private val cityDao: CityDao):ViewModel() {
    val allCities : LiveData<List<City>> = cityDao.getAllCities()
}

class CityViewModelFactory(private val cityDao: CityDao): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(CityViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CityViewModel(cityDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}