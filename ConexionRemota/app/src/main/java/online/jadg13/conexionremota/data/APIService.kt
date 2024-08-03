package online.jadg13.conexionremota.data

import online.jadg13.conexionremota.data.models.EstadoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("{endpoint}")
    suspend fun listarEstado(@Path("endpoint") endpoint: String):Response<EstadoResponse>

}
