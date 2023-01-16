package com.sberger.square_employeedirectory.data.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

// data class for Employee data
// mapped using Moshi from json response
@Parcelize
data class Employee(
    @Json(name = "uuid")
    val uuid: String,

    @Json(name = "full_name")
    val fullName: String,

    @Json(name = "phone_number")
    val phoneNumber: String?,

    @Json(name = "email_address")
    val emailAddress: String,

    @Json(name = "biography")
    val biography: String?,

    @Json(name = "photo_url_small")
    val photoUrlSmall: String?,

    @Json(name = "photo_url_large")
    val photoUrlLarge: String?,

    @Json(name = "team")
    val team: String,

    @Json(name = "employee_type")
    val employeeType: EmployeeType
) : Parcelable

enum class EmployeeType {
    FULL_TIME,
    PART_TIME,
    CONTRACTOR
}

data class EmployeeResponse(
    @Json(name = "employees")
    val employees: List<Employee>
)