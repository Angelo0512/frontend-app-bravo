package cr.una.bravo.bravofrontend.repository

import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.service.ServiceService
import cr.una.bravo.bravofrontend.service.ReportService

class ReportRepository(
    private val reportService: ReportService
) {
    suspend fun getAllReports() = reportService.getAllReports()

    suspend fun getReportById(id : Long) = reportService.getReportById(id)

    suspend fun createReport(reportRequest : Report) = reportService.createReport(reportRequest)
}