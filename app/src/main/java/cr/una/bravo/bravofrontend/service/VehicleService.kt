package cr.una.bravo.bravofrontend.service

import cr.una.bravo.bravofrontend.model.Vehicle
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleService {
    @GET("v1/vehicles")
    suspend fun getAllVehicles() : Response<List<Vehicle>>

     @GET("v1/vehicles/{id}")
     suspend fun getVehicleById(@Path("id") id: Long) : Response<Vehicle>

     companion object{
         private var vehicleService : VehicleService? = null
         fun getInstance() : VehicleService {
             if(vehicleService == null){
                 val retrofit = Retrofit.Builder()
                     .baseUrl("https://629f8590461f8173e4eb7fda.mockapi.io/")
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()
                 vehicleService = retrofit.create(VehicleService::class.java)
             }
             return vehicleService!!
         }
     }
}