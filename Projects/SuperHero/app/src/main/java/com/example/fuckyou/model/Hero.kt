package com.example.fuckyou


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.fuckyou.R


data class Hero(
    @StringRes val nameRes: Int,
    val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
object HeroesRepository {
    val heroes = listOf(
        Hero(
            nameRes = R.string.hero_1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        ),
        Hero(
            nameRes = R.string.hero_2,
            descriptionRes = R.string.description2,
            imageRes = R.drawable.android_superhero2
        ),
        Hero(
            nameRes = R.string.hero_3,
            descriptionRes = R.string.description3,
            imageRes = R.drawable.android_superhero3
        ),
        Hero(
            nameRes = R.string.hero_4,
            descriptionRes = R.string.description4,
            imageRes = R.drawable.android_superhero4
        ),
        Hero(
            nameRes = R.string.hero_5,
            descriptionRes = R.string.description5,
            imageRes = R.drawable.android_superhero5
        ),
        Hero(
            nameRes = R.string.hero_6,
            descriptionRes = R.string.description6,
            imageRes = R.drawable.android_superhero6
        )

    )
}