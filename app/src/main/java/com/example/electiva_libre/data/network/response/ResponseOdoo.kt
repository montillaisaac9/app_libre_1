package alcaravan.guiriri.contribuyente.data.networks.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ResponseOdoo<T>(
    val jsonrpc: String? =null,
    val result: T? = null,
    val error: ErrorOdoo? = null
    )

