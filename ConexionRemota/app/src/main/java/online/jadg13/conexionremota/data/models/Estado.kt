package online.jadg13.conexionremota.data.models

import com.google.gson.annotations.SerializedName

data class Estado(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String
)