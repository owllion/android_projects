package com.example.tiptime

import junit.framework.Assert.assertEquals
import org.junit.Test

class TipCalculatorTest {
    @Test
    fun calculate_30_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = "$2.00"
        val actualTip = calculateTip(amount,tipPercent,false)
        assertEquals(expectedTip,actualTip)
    }
}