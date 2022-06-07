package cr.una.bravo.bravofrontend.service

import cr.una.bravo.bravofrontend.model.Service
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceService {
    @GET("v1/services")
    suspend fun getAllServices() : Response<List<Service>>

    @GET("v1/services/{id}")
    suspend fun getServiceById(@Path("id") id: Long) : Response<Service>

    @POST("v1/services")
    suspend fun createService(@Body serviceRequest : Service) : Response<Service>

    companion object{
        var serviceService : ServiceService? = null
        fun getInstance() : ServiceService {
            if(serviceService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://629f8590461f8173e4eb7fda.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                serviceService = retrofit.create(ServiceService::class.java)
            }
            return serviceService!!
        }
    }
}