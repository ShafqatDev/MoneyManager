package com.example.expensemanager.presentation.main_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensemanager.presentation.add_expense_screen.AddExpenseScreen
import com.example.expensemanager.presentation.components.Screens
import com.example.expensemanager.presentation.expense_screen.ExpenseScreen

@Composable
fun NavHostHolder(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screens.ExpenseScreen.route
    ) {
        composable(Screens.ExpenseScreen.route) {
            ExpenseScreen(navController)
        }
        composable(Screens.AddExpenseScreen.route +"/{id}") {
            val id=it.arguments?.getString("id")
            id?.let {
                AddExpenseScreen(id = id, navController = navController)
            }
        }
    }
}