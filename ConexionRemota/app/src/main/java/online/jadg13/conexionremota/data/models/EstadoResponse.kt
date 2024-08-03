package online.jadg13.conexionremota.data.models

import com.google.gson.annotations.SerializedName

class EstadoResponse (
    @SerializedName("status") var status:String,
    @SerializedName("data") var estados: List<Estado>
)