package com.sberger.square_employeedirectory.data.repository

import android.util.Log
import com.sberger.square_employeedirectory.data.network.EmployeeDirectoryApi
import com.sberger.square_employeedirectory.data.network.model.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeesRepositoryImpl : EmployeesRepository {

    // implement repo functions

    // boolean will hold success/fail, list is the data we are requesting
    override suspend fun getEmployeeList(): Pair<Boolean, MutableList<Employee>> {
        var success: Boolean
        var result: MutableList<Employee>

        try {
            // run network call on IO thread
            withContext(Dispatchers.IO) {
                // call network to get list of employees
                val response = EmployeeDirectoryApi.retrofitService.getEmployeeListResponse()
                result = response.employees.toMutableList()
                result.sortBy { it.fullName } // sort employees alphabetically (by full name)

                success = true
                Log.d(this@EmployeesRepositoryImpl.javaClass.simpleName, "getEmployeeList success. ${result.size} employees retrieved}")
            }
        } catch (e: Exception) {
            result = mutableListOf()
            success = false
            Log.e(this@EmployeesRepositoryImpl.javaClass.simpleName, "getEmployeeList error: ${e.message}")
        }

        return Pair(success, result)
    }
}