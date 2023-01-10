package com.example.composebasic

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.data.EmptyGroup.name
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposebasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposebasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                ) {

                }
            }
        }
    }
}

@Composable
fun ShowArticleAndImg() {
    Column {
        Box(modifier = Modifier
            .border(width = 4.dp,
                color = Gray,
                shape = RoundedCornerShape(16.dp)
            )
        ){
        Image(
            
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription= null )
        }
        Text(
            fontSize=24.sp,
            fontWeight = FontWeight(500),
            text = "Jetpack Compose tutorial",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            modifier = Modifier.padding(start=16.dp,end=16.dp),
            textAlign = TextAlign.Justify
            )
        Text(text = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
            )
    }
}
@Composable
fun ShowCheck() {
    Column(
//        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Image(
                painter = painterResource(id = R.drawable.ic_task_completed) ,
                contentDescription =null,
//                Modifier.align(Alignment.Center)
            )

        Text(
            "All tasks completed",
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(top=24.dp, bottom = 8.dp)
        )
        Text("Nice work!", fontSize = 16.sp)
    }
}

@Composable
fun ShowComposeQuadrant () {
    Column(Modifier.fillMaxSize()) {
      Row(Modifier.weight(1f)) {
          ComposableInfoCard(
              title = stringResource(R.string.first_title),
              content = stringResource(R.string.first_content) ,
              backgroundColor = Color.Green,
              modifier = Modifier.weight(1f)
          )
          ComposableInfoCard(
              title = "Image composable",
              content = "Creates a composable that lays out and draws a given Painter class object.",
              backgroundColor =Yellow,
              modifier = Modifier.weight(1f)

          )

      }
      Row(Modifier.weight(1f)) {
          ComposableInfoCard(
              title = "Row composable",
              content = "A layout composable that places its children in a horizontal sequence." ,
              backgroundColor = Color.Cyan,
              modifier = Modifier.weight(1f)
          )
          ComposableInfoCard(
              title = "Column composable",
              content = "A layout composable that places its children in a vertical sequence.\n",
              backgroundColor =Color.LightGray,
              modifier = Modifier.weight(1f)

          )
      }



    }
    }
@Composable
fun ComposableInfoCard(
   title:String,
   content:String,
   backgroundColor: Color,
   modifier: Modifier = Modifier
){
    Column(modifier = modifier
        .fillMaxSize()
        .background(backgroundColor)
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold)
            Text(
                text = content,
                textAlign = TextAlign.Justify
            )
    }
}
@Preview(showBackground = true,showSystemUi = true)
@Composable()
fun DefaultPreview() {
    ComposebasicTheme {
//        ShowArticleAndImg()
//        ShowCheck()
        ShowComposeQuadrant()
    }
}