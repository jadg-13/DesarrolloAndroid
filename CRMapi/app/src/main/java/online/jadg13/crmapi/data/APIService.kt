package online.jadg13.crmapi.data

import online.jadg13.crmapi.data.models.CitiesResponse
import online.jadg13.crmapi.data.models.Data
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("{endpoint}")
    suspend fun listCities(
        @Path("endpoint") endpoint: String,
        @Query("name") searchQuery: String? = null
    ): Response<CitiesResponse>

    @POST("{endpoint}")
    suspend fun saveCity(@Path("endpoint") endpoint: String, @Body city: Data): Response<Data>

    @PUT("{endpoint}/{id}")
    suspend fun updateCity(@Path("endpoint") endpoint: String, @Path("id") id: Int, @Body city: Data ): Response<Data>

    @DELETE("{endpoint}/{id}")
    suspend fun deleteCity(@Path("endpoint") endpoint: String, @Path("id") id:Int): Response<Void>

    @GET("{endpoint}/{id}")
    suspend fun showCity(@Path("endpoint") endpoint: String, @Path("id") id: Int): Response<Data>
}