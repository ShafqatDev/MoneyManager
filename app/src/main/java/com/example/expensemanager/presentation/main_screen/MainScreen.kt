package com.example.expensemanager.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.expensemanager.presentation.main_screen.components.NavHostHolder

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHostHolder(navController = navController, modifier = modifier)
}

