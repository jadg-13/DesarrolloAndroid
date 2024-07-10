package online.jadg13.clase7.dao

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDataBase? =null

    fun getInstance(context: Context):AppDataBase{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}