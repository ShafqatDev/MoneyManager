package com.example.expensemanager.core.constants

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object MyLocalData {
    private const val MAIN_DATE_PATTERN = "EEEE, dd MMM yyy"

  @SuppressLint("SimpleDateFormat")
  fun getDateByLong(millis:Long):String{
    val date=Date(millis)
    val formatter=SimpleDateFormat(MAIN_DATE_PATTERN)
    formatter.timeZone= TimeZone.getTimeZone("UTC")
    return formatter.format(date)
  }

}