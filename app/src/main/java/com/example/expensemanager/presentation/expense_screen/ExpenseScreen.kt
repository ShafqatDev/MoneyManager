package com.example.expensemanager.presentation.expense_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expensemanager.presentation.components.Screens
import com.example.expensemanager.presentation.expense_card.ExpenseCard
import com.example.expensemanager.presentation.main_screen.components.TopBar

@Composable
fun ExpenseScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    val mainState = viewModel.state.collectAsState().value
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                onClick = {
                    navController.navigate("${Screens.AddExpenseScreen.route}/${-1}")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 15.dp,
                            vertical = 15.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Income")
                        Text(text = "Expense")
                        Text(text = "Balance")
                    }
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = mainState.totalIncome, color = Color.Green)
                        Text(text = mainState.totalExpense, color = Color.Red)
                        Text(text = mainState.totalBalance)
                    }
                }
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()) {
                items(mainState.itemsList) { expenseEntity ->
                    ExpenseCard(model = expenseEntity, modifier = Modifier.clickable {
                        navController.navigate("${Screens.AddExpenseScreen.route}/${expenseEntity.id}")
                    }, onDeleteClick = {
                        viewModel.deleteNote(expenseEntity)
                    })
                }
            }
        }
    }
}