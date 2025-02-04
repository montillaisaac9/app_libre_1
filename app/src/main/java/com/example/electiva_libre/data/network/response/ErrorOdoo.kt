package alcaravan.guiriri.contribuyente.data.networks.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorOdoo(
    @SerialName("code")
    var code: Int,
    @SerialName("message")
    var message: String,
    @SerialName("data")
    var dataError: DataError?
)