package com.example.expensemanager.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.expensemanager.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Upsert
    suspend fun insertExpense(expenseEntity: ExpenseEntity)
    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)
    @Query("SELECT * FROM ExpenseEntity WHERE id=:mId")
    suspend fun getThisIdData(mId: String): List<ExpenseEntity>

    @Query("SELECT * FROM ExpenseEntity")
    fun getAllExpense():Flow<List<ExpenseEntity>>
}