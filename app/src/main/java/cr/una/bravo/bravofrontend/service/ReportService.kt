package cr.una.bravo.bravofrontend.service

import cr.una.bravo.bravofrontend.model.Report
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReportService {
    @GET("v1/reports")
    suspend fun getAllReports() : Response<List<Report>>

    @GET("v1/reports/{id}")
    suspend fun getReportById(@Path("id") id: Long) : Response<Report>

    @POST("v1/reports")
    suspend fun createReport(@Body reportRequest : Report) : Response<Report>

    companion object{
        var reportService : ReportService? = null
        fun getInstance() : ReportService {
            if(reportService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://629f8590461f8173e4eb7fda.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                reportService = retrofit.create(ReportService::class.java)
            }
            return reportService!!
        }
    }
}