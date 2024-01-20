package com.example.expensemanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val entryType:String,
    val title:String,
    val amount:Int,
    val savedTime:String
)
