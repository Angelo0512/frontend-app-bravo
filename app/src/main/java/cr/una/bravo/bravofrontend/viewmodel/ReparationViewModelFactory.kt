package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.bravo.bravofrontend.repository.ReportRepository
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.ReportService
import cr.una.bravo.bravofrontend.service.VehicleService

@Suppress("UNCHECKED_CAST")
class ReparationViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ReparationViewModel::class.java)){
            ReparationViewModel(
                reportRepository = ReportRepository(
                    reportService =  ReportService.getInstance()
                )
            ) as T
        } else {
            throw IllegalAccessException("ViewModel Not Found")
        }
    }
}