package com.example.expensemanager.presentation.components

sealed class MainScreenEvents {
    data class TitleValue(val text: String) : MainScreenEvents()
    data class AmountValue(val text: String) : MainScreenEvents()
    data object Save : MainScreenEvents()
}