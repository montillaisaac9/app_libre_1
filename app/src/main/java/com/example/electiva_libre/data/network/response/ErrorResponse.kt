package alcaravan.guiriri.contribuyente.data.networks.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("detail")
    val detail: String
)