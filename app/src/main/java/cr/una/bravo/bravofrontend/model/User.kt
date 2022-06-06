package cr.una.bravo.bravofrontend.model

import java.util.*

data class UserLogin(
    var email: String,
    var password: String,
)

data class User(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var enabled: Boolean,
    var tokenExpired: Boolean,
    var createDate: Date,
    var roles: List<Roles>,
)
data class UserBasic(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var createDate: Date,
)

data class Roles(
    var id: Long,
    var name: String,
    var privilege: List<Privilege>,
)

data class Privilege(
    var id: Long,
    var name: String,
)