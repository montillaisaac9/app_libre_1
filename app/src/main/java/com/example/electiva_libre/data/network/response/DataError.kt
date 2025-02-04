package alcaravan.guiriri.contribuyente.data.networks.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataError(
    @SerialName("debug")
    val debug: String,

    @SerialName("arguments")
    val arguments: List<String>,

    @SerialName("exception_type")
    val exceptionType: String,

    @SerialName("name")
    val name: String,

    @SerialName("message")
    val message: String?
)
