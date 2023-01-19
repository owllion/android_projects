package com.example.jettodo

import android.app.Application // -> extends the entire android ecosystem
import dagger.hilt.android.HiltAndroidApp
/*
* @HiltAndroidApp  ->
* gives Hilt access to the entire application,
* hence we are extending app class.
* So it creates a 'dependency container' as it's called at the top level.
* In other words, Hilt now supply dependency to any part of the app.
* */
/*
*官方: Additionally, it is the parent component of the app,
* which means that other components can access the dependencies that it provides.
**/
@HiltAndroidApp
class TodoApplication:Application() {
}