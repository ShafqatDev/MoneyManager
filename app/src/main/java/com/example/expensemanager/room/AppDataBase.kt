package com.example.expensemanager.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensemanager.data.datasource.ExpenseDao
import com.example.expensemanager.data.model.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun expenseDao():ExpenseDao
}