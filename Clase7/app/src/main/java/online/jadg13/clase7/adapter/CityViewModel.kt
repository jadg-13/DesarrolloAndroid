package online.jadg13.clase7.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import online.jadg13.clase7.dao.CityDao
import online.jadg13.clase7.entities.City

class CityViewModel(private val cityDao: CityDao):ViewModel() {
    val allCities : LiveData<List<City>> = cityDao.getAllCities()

    fun getCity(id: Int): LiveData<City>{
        return cityDao.getCity(id)
    }

    fun updateCity(city: City){
        viewModelScope.launch(Dispatchers.IO) {
            cityDao.update(city)
        }
    }

    fun deleteCity(city: City){
        viewModelScope.launch(Dispatchers.IO){
            cityDao.delete(city)
        }
    }
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