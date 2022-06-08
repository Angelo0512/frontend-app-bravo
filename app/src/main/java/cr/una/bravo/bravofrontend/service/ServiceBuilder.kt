package cr.una.bravo.bravofrontend.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cr.una.bravo.bravofrontend.BuildConfig
import cr.una.bravo.bravofrontend.BuildConfig.DATE_FORMAT
import cr.una.bravo.bravofrontend.utils.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * We create a builder of the retrofit object which can be reused for all method calls
 * declared in the RestApi interface.
 */
object ServiceBuilder {
    var gson: Gson = GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .create()

    // If you need to check your request change the Level
    var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.NONE
    )

    private val client =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(AuthorizationInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://629f8590461f8173e4eb7fda.mockapi.io/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}