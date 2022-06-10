package cr.una.bravo.bravofrontend.service

import cr.una.bravo.bravofrontend.model.User
import cr.una.bravo.bravofrontend.model.UserBasic
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("v1/users")
    suspend fun getAllUsers() : Response<List<UserBasic>>

    @GET("v1/users/{id}")
    suspend fun getUserById(@Path("id") id: Long) : Response<UserBasic>

    @POST("v1/users")
    suspend fun createUser(@Body UserRequest : User) : Response<User>


    @GET("v1/clients")
    suspend fun getAllClients() : Response<List<UserBasic>>

    @GET("v1/clients/{id}")
    suspend fun getClientById(@Path("id") id: Long) : Response<UserBasic>

    @POST("v1/clients")
    suspend fun createClient(@Body UserRequest : User) : Response<User>


    companion object{
        var userService : UserService? = null
        fun getInstance() : UserService {
            if(userService == null){
                userService = ServiceBuilder.buildService(UserService::class.java)
            }
            return userService!!
        }
    }
}