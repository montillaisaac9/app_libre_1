package alcaravan.guiriri.contribuyente.data.networks.response

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class RequestBody<T> @OptIn(ExperimentalSerializationApi::class) constructor(

    @EncodeDefault val jsonrpc: String = "2.0",
    @EncodeDefault val method: String = "call",
    @EncodeDefault val params: T
)
