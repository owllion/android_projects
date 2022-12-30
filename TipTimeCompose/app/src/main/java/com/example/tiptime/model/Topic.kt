package com.example.tiptime.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val availableCourses: Int,
    @DrawableRes val imageResourceId: Int,
)
