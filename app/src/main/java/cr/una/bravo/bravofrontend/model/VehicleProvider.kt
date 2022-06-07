package cr.una.bravo.bravofrontend.model

class VehicleProvider {
    companion object {
        fun findVehicleById(id: Int): Vehicle {
            return vehicleList[id]
        }

        fun findAllVehicles(): List<Vehicle> {
            return vehicleList
        }

        val vehicleList = listOf<Vehicle>(
            Vehicle(
                1,
                "Placa 1",
                "Vin Number 1",
                "Brand 1",
                "Serial Motor 1",
                "Vehicle Class 1",
                "Motor Type 1"
            ),
            Vehicle(
                2,
                "Placa 2",
                "Vin Number 2",
                "Brand 2",
                "Serial Motor 2",
                "Vehicle Class 2",
                "Motor Type 2",
            ),
            Vehicle(
                3,
                "Placa 3",
                "Vin Number 3",
                "Brand 3",
                "Serial Motor 3",
                "Vehicle Class 3",
                "Motor Type 3",
            ),
            Vehicle(
                4,
                "Placa 4",
                "Vin Number 4",
                "Brand 4",
                "Serial Motor 4",
                "Vehicle Class 4",
                "Motor Type 4",
            ),
        )
    }
}