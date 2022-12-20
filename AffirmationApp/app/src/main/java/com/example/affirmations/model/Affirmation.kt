package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//When you create an instance of Affirmation,
// you need to pass in the resource ID for the affirmation string.
// The resource ID is an integer.
data class Affirmation(@StringRes val stringResourceId: Int,@DrawableRes val imageResourceId: Int) {
}