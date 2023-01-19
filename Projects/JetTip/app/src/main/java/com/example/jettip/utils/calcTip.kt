package com.example.jettip.utils

import android.util.Log
import java.text.NumberFormat

fun calcTip(totalBill:Double,tipPercentage:Int):String {
    Log.d("CalcTip","This is calcTip: ${( totalBill*tipPercentage)/100}")
    Log.d("CalcTip-2","This is calcTip參數: $tipPercentage  and $totalBill")
    val tip  = if(totalBill> 1 && totalBill.toString().isNotEmpty()) (totalBill*tipPercentage)/100 else 0
    return NumberFormat.getCurrencyInstance().format(tip)
}