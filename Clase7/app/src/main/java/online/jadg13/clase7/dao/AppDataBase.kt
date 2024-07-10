package online.jadg13.clase7.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import online.jadg13.clase7.entities.City

@Database(entities = [City::class], version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun cityDao():CityDao
}