package com.sberger.square_employeedirectory.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sberger.square_employeedirectory.MainCoroutineDispatcherRule
import com.sberger.square_employeedirectory.data.network.model.Employee
import com.sberger.square_employeedirectory.data.network.model.EmployeeType
import com.sberger.square_employeedirectory.data.repository.EmployeesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class EmployeeListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutineDispatcherRule()

    @Test
    fun `getEmployeeList when call is successful and data is not empty should set proper data`() = runTest {
        val success = true
        val data = mutableListOf(
            Employee(
                "123",
                "name",
                "6145551234",
                "name@squareup.com",
                "I am a person",
                "http://squareup.com/image_small.jpg",
                "http://squareup.com/image_large.jpg",
                "Restaurant",
                EmployeeType.FULL_TIME
            )
        )
        val result = Pair(success, data)

        val mockRepo = mock(EmployeesRepository::class.java)
        given(mockRepo.getEmployeeList()).willReturn(result)
        val employeeListViewModel = EmployeeListViewModel(mockRepo)

        employeeListViewModel.getEmployeeList()
        advanceUntilIdle()

        assertEquals(data, employeeListViewModel.employees.value)
        assertEquals(!success, employeeListViewModel.isError.value)
    }

    @Test
    fun `getEmployeeList when call is successful and data is empty should set proper data`() = runTest {
        val success = true
        val data = mutableListOf<Employee>()
        val result = Pair(success, data)

        val mockRepo = mock(EmployeesRepository::class.java)
        given(mockRepo.getEmployeeList()).willReturn(result)
        val employeeListViewModel = EmployeeListViewModel(mockRepo)

        employeeListViewModel.getEmployeeList()
        advanceUntilIdle()

        assertEquals(data, employeeListViewModel.employees.value)
        assertEquals(!success, employeeListViewModel.isError.value)
    }

    @Test
    fun `getEmployeeList when call is unsuccessful and data is not empty should set proper data`() = runTest {
        val success = false
        val data = mutableListOf<Employee>()
        val result = Pair(success, data)

        val mockRepo = mock(EmployeesRepository::class.java)
        given(mockRepo.getEmployeeList()).willReturn(result)
        val employeeListViewModel = EmployeeListViewModel(mockRepo)

        employeeListViewModel.getEmployeeList()
        advanceUntilIdle()

        assertEquals(data, employeeListViewModel.employees.value)
        assertEquals(!success, employeeListViewModel.isError.value)
    }

    @Test
    fun `getEmployeeList when call is unsuccessful and data is empty should set proper data`() = runTest {
        val success = false
        val data = mutableListOf<Employee>()
        val result = Pair(success, data)

        val mockRepo = mock(EmployeesRepository::class.java)
        given(mockRepo.getEmployeeList()).willReturn(result)
        val employeeListViewModel = EmployeeListViewModel(mockRepo)

        employeeListViewModel.getEmployeeList()
        advanceUntilIdle()

        assertEquals(data, employeeListViewModel.employees.value)
        assertEquals(!success, employeeListViewModel.isError.value)
    }
}