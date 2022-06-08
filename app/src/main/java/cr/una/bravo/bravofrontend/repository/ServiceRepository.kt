package cr.una.bravo.bravofrontend.repository

import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.service.ServiceService

class ServiceRepository(
    private val serviceService: ServiceService
) {
    suspend fun getAllServices() = serviceService.getAllServices()

    suspend fun getServiceById(id : Long) = serviceService.getServiceById(id)

    suspend fun createService(serviceRequest : Service) = serviceService.createService(serviceRequest)
}