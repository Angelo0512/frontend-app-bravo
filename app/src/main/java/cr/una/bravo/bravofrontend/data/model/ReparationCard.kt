package cr.una.bravo.bravofrontend.data.model

var reparationCardList = mutableListOf<ReparationCard>()

data class ReparationCard(
    var reparationId: String,
    var clientId: String,
    var clientName: String,
    var vehiclePlate: String
)