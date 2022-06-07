package cr.una.bravo.bravofrontend.model

import java.util.*

class ClientProvider {
    companion object{
        fun findClientById(id: Int): UserBasic {
            return clientList[id]
        }

        fun findAllClients(): List<UserBasic> {
            return clientList
        }

        val clientList = listOf<UserBasic>(
            UserBasic(
                1,
                "John",
                "Doe",
                "myemail@test.com",
                Date()
            ),
            UserBasic(
                2,
                "John",
                "Doe",
                "myemail@test.com",
                Date()
            ),
            UserBasic(
                3,
                "John",
                "Doe",
                "myemail@test.com",
                Date()
            ),
            UserBasic(
                4,
                "John",
                "Doe",
                "myemail@test.com",
                Date()
            ),
        )
    }

}