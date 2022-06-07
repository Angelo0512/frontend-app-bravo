package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.model.ReportProvider

class ReparationViewModel : ViewModel() {
    val report = MutableLiveData<Report>()
    val reportList = MutableLiveData<List<Report>>()

    fun getTask() {
        val position = (0..2).random()
        val _report = ReportProvider.findReportById(position)
        report.postValue(_report)
    }

    fun findAllTask() {
        val _reportList = ReportProvider.findAllReports()
        reportList.postValue(_reportList)
    }
}