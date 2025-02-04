package com.example.electiva_libre.di.modules

import android.content.Context
import android.util.Log
import com.example.electiva_libre.data.network.client.ApiServiceImpl
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class) //makes the module available throughout the application until the application is destroyed.
object ApiModule {
    private const val TIME_OUT = 60_000
    /**
     * [Singleton] is a useful tool to ensure that a class only has one instance in the entire application
     * [Provides] is used to indicate that a method is responsible for providing an instance of a certain class.
     * [provideHttpClient] Used to provide the Ktor client for the ApiService implementation class
     * @return [HttpClient] instance
     */
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android){
            /**
             * This section configures the connection engine used by the HTTP client.
             * In this case, the connection and socket timeouts are set to TIME_OUT milliseconds.
             */
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
            install(Logging){
                /**
                 * Aquí se define un objeto personalizado que implementa la interfaz Logger.
                 * Este objeto es responsable de manejar los mensajes de log generados por el cliente HTTP.
                 * En este caso, el mensaje se envía al log de Android utilizando Log.v().
                 */
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }

                }
                /**
                 * This line sets the log level to LogLevel.ALL,
                 * which means that all log messages will be logged,
                 * from the most detailed to the least important.
                 */
                level= LogLevel.ALL
            }

            install(DefaultRequest){
                /**
                 * Sets the URL for the request.
                 * [BuildConfig.HOST_BASE] is a constant defined in your build.gradle file,
                 * typically used to store the base URL of your API
                 */
                //url(BuildConfig.HOST_BASE)
                /**
                 *Adds a header to the request indicating that the content type is JSON.
                 * This is important for the server to understand the format of the data being sent.
                 */
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Application.Json)
            }
            /**
             * This is enough to allow the Ktor client to maintain cookies between requests.
             * You can find the full example here: client-cookies.
             */
            install(HttpCookies)




            /**
             * Is used to configure the serializer/deserializer used.
             * In this case, this is Kotlinx JSON serialization
             */
            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    encodeDefaults = true
                    explicitNulls = true
                })
            }
            /**
             *  configura un observador de respuestas HTTP en tu cliente Ktor.
             *  Esto significa que después de que se completa una solicitud HTTP,
             *  se ejecutará el código dentro del bloque onResponse.
             */
            install(ResponseObserver) {
                /**
                 * Define una función que se ejecutará cuando se reciba una respuesta HTTP.
                 * El parámetro response contiene información sobre la respuesta,
                 * incluyendo el código de estado HTTP.
                 */
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
        }
    }

    /**
     * [Singleton] is a useful tool to ensure that a class only has one instance in the entire application
     * [Provides] is used to indicate that a method is responsible for providing an instance of a certain class.
     * [provideApiService] Used to provide the ApiServiceImpl implementation class
     * @param httpClient [HttpClient] instance
     * @return [ApiServiceImpl] instance
     */
    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient, @ApplicationContext context: Context): ApiServiceImpl = ApiServiceImpl(httpClient, context)

    /**
     * [Provides] is used to indicate that a method is responsible for providing an instance of a certain class.
     * @return [HttpClient] instance
     */
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.Default
}