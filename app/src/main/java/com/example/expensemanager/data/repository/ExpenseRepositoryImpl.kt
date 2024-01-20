package com.example.expensemanager.data.repository

import com.example.expensemanager.data.datasource.ExpenseDao
import com.example.expensemanager.data.model.ExpenseEntity
import com.example.expensemanager.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class ExpenseRepositoryImpl constructor(
    private val dao: ExpenseDao
) : ExpenseRepository {
    override suspend fun insertExpense(expenseEntity: ExpenseEntity) {
        dao.insertExpense(expenseEntity)
    }

    override suspend fun deleteExpense(expenseEntity: ExpenseEntity) {
        return dao.deleteExpense(expenseEntity)
    }

    override suspend fun getExpenseById(mId: String): List<ExpenseEntity> {
        return dao.getThisIdData(mId)
    }

    override suspend fun getAllExpense(): Flow<List<ExpenseEntity>> {
        return dao.getAllExpense()
    }
}