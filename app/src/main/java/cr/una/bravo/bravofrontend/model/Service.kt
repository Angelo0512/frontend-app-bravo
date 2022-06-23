package cr.una.bravo.bravofrontend.model

data class Service(
    var id: Long? = null,
    var name: String,
    var observations: String?,
    var state: String,
)

data class ServiceData(
    var id: Long,
    var name: String
)

