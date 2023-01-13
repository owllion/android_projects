package com.example.movieapp.navigation.screens.home.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(
    navigateUp: () -> Unit,
    movieName: String?,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Row(modifier =Modifier.padding(start = 10.dp),horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null )
                }
                Text(text = "Back")

            }
        }
    }) {
        Surface(modifier = modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    text = movieName.toString(),
                    //可能是Null，所以要寫toString() 才可以印出null
                    //例如fun main() {
                    //    printText(null) //null
                    //}
                    //fun printText(text: String?)  = print(text.toString())
                    style = MaterialTheme.typography.h2
                )


            }

        }
    }
   


}