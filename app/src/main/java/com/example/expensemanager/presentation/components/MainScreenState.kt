package com.example.expensemanager.presentation.components

import com.example.expensemanager.data.model.ExpenseEntity

data class MainScreenState(
    val itemsList: List<ExpenseEntity> = emptyList(),
    val totalIncome: String = "0",
    val totalExpense: String = "0",
    val totalBalance: String = "0"
)