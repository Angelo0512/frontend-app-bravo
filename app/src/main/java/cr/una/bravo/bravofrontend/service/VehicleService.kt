package cr.una.bravo.bravofrontend.service

import cr.una.bravo.bravofrontend.model.Vehicle
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VehicleService {
    @GET("v1/vehicles")
    suspend fun getAllVehicles() : Response<List<Vehicle>>

     @GET("v1/vehicles/{id}")
     suspend fun getVehicleById(@Path("id") id: Long) : Response<Vehicle>

     @POST("v1/vehicles")
     suspend fun createVehicle(@Body vehicleRequest : Vehicle) : Response<Vehicle>

     companion object{
         var vehicleService : VehicleService? = null
         fun getInstance() : VehicleService {
             if(vehicleService == null){
                 vehicleService = ServiceBuilder.buildService(VehicleService::class.java)
             }
             return vehicleService!!
         }
     }
}