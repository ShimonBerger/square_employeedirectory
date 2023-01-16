package com.sberger.square_employeedirectory.data.repository

import com.sberger.square_employeedirectory.data.network.model.Employee

interface EmployeesRepository {
    suspend fun getEmployeeList(): Pair<Boolean, MutableList<Employee>>
}