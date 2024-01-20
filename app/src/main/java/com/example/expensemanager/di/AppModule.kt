package com.example.expensemanager.di

import android.content.Context
import androidx.room.Room
import com.example.expensemanager.data.datasource.ExpenseDao
import com.example.expensemanager.domain.repository.ExpenseRepository
import com.example.expensemanager.data.repository.ExpenseRepositoryImpl
import com.example.expensemanager.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getExpenseDao(appDataBase: AppDataBase):ExpenseDao{
        return appDataBase.expenseDao()
    }
    @Provides
    @Singleton
    fun getDb(@ApplicationContext context: Context):AppDataBase{
        return Room.databaseBuilder(context,AppDataBase::class.java,"app_db").build()
    }
    @Provides
    @Singleton
    fun provideExpenseRepositoryImpl(dao: ExpenseDao): ExpenseRepository {
        return ExpenseRepositoryImpl(dao)
    }
}