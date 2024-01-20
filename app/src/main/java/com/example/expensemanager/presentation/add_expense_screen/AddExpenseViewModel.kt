package com.example.expensemanager.presentation.add_expense_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.core.constants.MyLocalData
import com.example.expensemanager.data.model.ExpenseEntity
import com.example.expensemanager.domain.repository.ExpenseRepository
import com.example.expensemanager.presentation.main_screen.components.EntryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository,
) : ViewModel() {

    data class AddScreenState(
        val id: Int = -1,
        val title: String = "",
        val amount: String = "",
        val selected: EntryType = EntryType.Income,
    )

    private val _state = MutableStateFlow(AddScreenState())
    val state = _state.asStateFlow()

    fun onAmountChanged(amount: String) {
        _state.update {
            it.copy(
                amount = amount
            )
        }
    }

    fun onTitleChanged(title: String) {
        _state.update {
            it.copy(
                title = title
            )
        }
    }

    fun onSaveItem() {
        viewModelScope.launch {
            repository.insertExpense(
                ExpenseEntity(
                    id = 0,
                    entryType = state.value.selected.name,
                    title = state.value.title,
                    amount = state.value.amount.toInt(),
                    savedTime = MyLocalData.getDateByLong(Calendar.getInstance().timeInMillis)
                )
            )
        }
    }

    fun onUpdateItem() {
        viewModelScope.launch {
            repository.insertExpense(
                ExpenseEntity(
                    id = state.value.id.toLong(),
                    entryType = state.value.selected.name,
                    title = state.value.title,
                    amount = state.value.amount.toInt(),
                    savedTime = MyLocalData.getDateByLong(Calendar.getInstance().timeInMillis)
                )
            )
        }
    }

    fun getThiDataId(id: String) {
        viewModelScope.launch {
            val save = repository.getExpenseById(id)
            val entity = save[0]
            if (id.isNotEmpty()) {
                _state.update {
                    it.copy(
                        id = entity.id.toInt(),
                        title = entity.title,
                        amount = entity.amount.toString()
                    )
                }
            }
        }
    }
    fun select(entryType: EntryType) {
        _state.update {
            it.copy(
                selected = entryType
            )
        }
    }
}
