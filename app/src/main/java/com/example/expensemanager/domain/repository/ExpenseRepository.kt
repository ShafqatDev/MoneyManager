package com.example.expensemanager.domain.repository

import com.example.expensemanager.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun insertExpense(expenseEntity: ExpenseEntity)
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)
    suspend fun getExpenseById(mId: String): List<ExpenseEntity>
    suspend fun getAllExpense(): Flow<List<ExpenseEntity>>
}