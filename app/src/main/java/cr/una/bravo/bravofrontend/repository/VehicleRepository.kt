package cr.una.bravo.bravofrontend.repository

import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.service.VehicleService

class VehicleRepository(
    private val vehicleService: VehicleService
) {
    suspend fun getAllVehicles() = vehicleService.getAllVehicles()

    suspend fun getVehicleById(id : Long) = vehicleService.getVehicleById(id)

    suspend fun createVehicle(vehicleRequest : Vehicle) = vehicleService.createVehicle(vehicleRequest)
}