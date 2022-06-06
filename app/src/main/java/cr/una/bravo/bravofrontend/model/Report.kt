package cr.una.bravo.bravofrontend.model

import java.util.*

data class Report (
    var id: Long,
    var creationDate: Date,
    var description: String,
    var services: List<Service>,
    var client: UserBasic,
    var vehicle: Vehicle,
    var technician: UserBasic,
)