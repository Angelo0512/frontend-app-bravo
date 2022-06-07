package cr.una.bravo.bravofrontend.model

import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class ReportProvider {
    companion object {
        fun findReportById(id: Int): Report {
            return reportList[id]
        }

        fun findAllReports(): List<Report> {
            return reportList
        }

        val reportList = listOf<Report>(
            Report(
                1,
                Date(),
                "Revision de motor",
                null,
                UserBasic(
                    1,
                    "John",
                    "Doe",
                    "myemail@test.com",
                    Date()
                ),
                Vehicle(
                    1,
                    "1234",
                    "12456123",
                    "Motorola",
                    "12345",
                    "Deportivo",
                    "Electrico",
                ),
                UserBasic(
                    2,
                    "Jane",
                    "Doe",
                    "jane@test.com",
                    Date()
                ),
            ),
            Report(
                2,
                Date(),
                "Revision de motor",
                null,
                UserBasic(
                    2,
                    "John",
                    "Doe",
                    "myemail@test.com",
                    Date()
                ),
                Vehicle(
                    1,
                    "1235",
                    "12456123",
                    "Motorola",
                    "12345",
                    "Deportivo",
                    "Electrico",
                ),
                UserBasic(
                    2,
                    "Jane",
                    "Doe",
                    "jane@test.com",
                    Date()
                ),
            ),
            Report(
                3,
                Date(),
                "Revision de motor",
                null,
                UserBasic(
                    3,
                    "John",
                    "Doe",
                    "myemail@test.com",
                    Date()
                ),
                Vehicle(
                    1,
                    "1236",
                    "12456123",
                    "Motorola",
                    "12345",
                    "Deportivo",
                    "Electrico",
                ),
                UserBasic(
                    2,
                    "Jane",
                    "Doe",
                    "jane@test.com",
                    Date()
                ),
            ),
        )
    }
}