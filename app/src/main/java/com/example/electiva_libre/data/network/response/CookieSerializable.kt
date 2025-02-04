package com.example.electiva_libre.data.network.response

import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding
import io.ktor.util.date.GMTDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class CookieSerializable(
    val name: String,
    val value: String,
    val encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
    val maxAge: Int = 0,
    val expires: Long? = null,
    val domain: String? = null,
    val path: String? = null,
    val secure: Boolean = false,
    val httpOnly: Boolean = false,
) {
    companion object
    {
        fun serializeCookie (cookie: Cookie): String
        {
            val nowTimestamp = GMTDate().timestamp + (1000 * 20 * 60)
            val temp = CookieSerializable(
                cookie.name,
                cookie.value,
                cookie.encoding,
                cookie.maxAge,
                if (cookie.expires == null) nowTimestamp else cookie.expires?.timestamp,
                cookie.domain,
                cookie.path,
                cookie.secure,
                cookie.httpOnly
            )

            return Json.encodeToString(temp)
        }

        fun deserializeCookie (cookieString: String?): Cookie?
        {
            if (cookieString == null) return null

            val temp = Json.decodeFromString<CookieSerializable>(cookieString)
            return Cookie(
                temp.name,
                temp.value,
                CookieEncoding.URI_ENCODING,
                temp.maxAge,
                if (temp.expires == null) null else GMTDate(temp.expires),
                temp.domain,
                temp.path,
                temp.secure,
                temp.httpOnly
            )
        }
    }
}
