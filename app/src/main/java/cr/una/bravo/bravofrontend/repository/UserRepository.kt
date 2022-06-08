package cr.una.bravo.bravofrontend.repository

import cr.una.bravo.bravofrontend.model.User
import cr.una.bravo.bravofrontend.service.UserService

class UserRepository(
    private val userService: UserService
) {
    suspend fun getAllUsers() = userService.getAllUsers()

    suspend fun getUserById(id : Long) = userService.getUserById(id)

    suspend fun createUser(userRequest : User) = userService.createUser(userRequest)
}