package online.jadg13.crmapi.data.models


data class Data(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val created_at: String? = null,
    val updated_at: String? = null
)