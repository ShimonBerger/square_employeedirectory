package com.sberger.square_employeedirectory.di

import com.sberger.square_employeedirectory.data.repository.EmployeesRepository
import com.sberger.square_employeedirectory.data.repository.EmployeesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideEmployeesRepository(): EmployeesRepository = EmployeesRepositoryImpl()
}