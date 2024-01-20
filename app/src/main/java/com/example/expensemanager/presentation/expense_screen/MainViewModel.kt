package com.example.expensemanager.presentation.expense_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.data.datasource.ExpenseDao
import com.example.expensemanager.data.model.ExpenseEntity
import com.example.expensemanager.domain.repository.ExpenseRepository
import com.example.expensemanager.presentation.components.MainScreenState
import com.example.expensemanager.presentation.main_screen.components.EntryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {
    private val mainState = MutableStateFlow(MainScreenState())
    val state = mainState.asStateFlow()

    init {
        getAllData()
    }

    private fun getAllData() {
        viewModelScope.launch {
            repository.getAllExpense().collectLatest { list ->
                val expense = 0
                var income = 0
                mainState.update {
                    it.copy(
                        itemsList = list
                    )
                }
                for (items in list) {
                    if (items.entryType == EntryType.Expense.name) {
                        income += items.amount
                    } else {
                        income += items.amount
                    }
                }
                val balance = income - expense
                mainState.update {
                    it.copy(
                        totalIncome = income.toString(),
                        totalExpense = expense.toString(),
                        totalBalance = balance.toString()
                    )
                }
            }
        }
    }

    fun deleteNote(expenseEntity: ExpenseEntity) {
        viewModelScope.launch{
            repository.deleteExpense(expenseEntity)
        }
        val list = state.value.itemsList.toMutableList()
        val index = list.indexOfFirst {
            it.id == expenseEntity.id
        }
        list.removeAt(index)
        mainState.update {
            it.copy(
                itemsList = list
            )
        }
    }
}