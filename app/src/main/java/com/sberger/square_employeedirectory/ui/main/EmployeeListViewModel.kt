package com.sberger.square_employeedirectory.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sberger.square_employeedirectory.data.network.model.Employee
import com.sberger.square_employeedirectory.data.repository.EmployeesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(private val repo: EmployeesRepository) : ViewModel() {

    // internal (mutable) data from data layer
    private val _employees = MutableLiveData<List<Employee>>(mutableListOf())

    // exposed LiveData for UI layer (immutable)
    val employees: LiveData<List<Employee>> = _employees

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isError: MutableLiveData<Boolean> = MutableLiveData(false)

    // get employee list when initialized
    init {
        getEmployeeList()
    }

    fun getEmployeeList() {
        isLoading.value = true

        viewModelScope.launch {
            val result = repo.getEmployeeList()

            if (result.first) {
                // success
                Log.d(this@EmployeeListViewModel.javaClass.simpleName, "getEmployeeList success. ${result.second.size} employees retrieved}")

                // set LiveData to trigger UI updates
                isError.value = false
                _employees.value = result.second
                isLoading.value = false
            } else {
                // fail
                Log.e(this@EmployeeListViewModel.javaClass.simpleName, "getEmployeeList fail.")

                // set LiveData to trigger UI updates
                isError.value = true
                isLoading.value = false
            }
        }
    }

}