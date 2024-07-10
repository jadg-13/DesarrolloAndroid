package online.jadg13.clase7.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import online.jadg13.clase7.entities.City

@Dao
abstract class CityDao {

    @Insert
    abstract suspend fun insert(city: City)

    @Query("select * from cities")
    abstract suspend fun getAllCities():List<City>

    @Update
    abstract suspend fun update(city: City)

    @Delete
    abstract suspend fun delete(city: City)

}