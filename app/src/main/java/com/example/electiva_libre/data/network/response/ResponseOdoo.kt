package alcaravan.guiriri.contribuyente.data.networks.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Response<T>(
    val result: T? = null,
    val error: String? = null
    )

