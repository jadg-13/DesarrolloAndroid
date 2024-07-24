package online.jadg13.conexionremota.data

import online.jadg13.conexionremota.data.models.Estados
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("test/all")
    suspend fun listarEstado():Estados

}

object RetrofitServiceFactory{

    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://jadg13.online/crm/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}