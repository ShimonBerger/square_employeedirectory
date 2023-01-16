package com.sberger.square_employeedirectory.data.network

import com.sberger.square_employeedirectory.data.network.model.EmployeeResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface IEmployeeDirectoryApi {
    // endpoint for employee list
    @GET("employees.json")

    // endpoint for malformed employee list (for testing)
//     @GET("employees_malformed.json")

    // endpoint for empty employee list (for testing)
//     @GET("employees_empty.json")
    suspend fun getEmployeeListResponse(): EmployeeResponse
}

object EmployeeDirectoryApi {
    val retrofitService: IEmployeeDirectoryApi by lazy {
        retrofit.create(IEmployeeDirectoryApi::class.java)
    }
}