package cr.una.bravo.bravofrontend.repository

import cr.una.bravo.bravofrontend.model.User
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.service.UserService
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

class UserRepository(
    private val userService: UserService
) {
    suspend fun getAllUsers() = userService.getAllUsers()

    suspend fun getUserById(id : Long) = userService.getUserById(id)

    suspend fun createUser(userRequest : User) = userService.createUser(userRequest)


    suspend fun getAllClients()  = userService.getAllClients()


    suspend fun getClientById(id: Long) = userService.getClientById(id)


    suspend fun createClient(userRequest : User) = userService.createClient(userRequest)
}