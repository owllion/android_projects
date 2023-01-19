package com.example.jettip.utils

fun calTotalPerPerson(
    amount: Double,
    splitBy: Int,
    tipPercent: Int
):Double {
    //先算出整頓飯錢+全部的小費，在去除以split人數
    val finalTotal = calcTip(totalBill = amount, tipPercentage = tipPercent).toDouble()+amount
    //finalTotal 是string，要轉
    return (finalTotal.toDouble() / splitBy)
}