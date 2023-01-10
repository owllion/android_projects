package com.example.circleclick

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.circleclick.ui.theme.CircleClickTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircleClickTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   CircleClick()
                }
            }
        }
    }
}

@Composable
fun CircleClick() {
    var myMoney by remember { mutableStateOf(0) }
   Surface(
       modifier = Modifier
           .padding(20.dp)
           .fillMaxSize()
           .background(Color.Yellow),
       shape = RoundedCornerShape(10.dp)

   ) {
       Column(
           modifier =Modifier.background(Color.DarkGray),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(
               text = "$$myMoney",
               style= TextStyle(
               fontSize = 80.sp,
                   color = Color.White,
               fontWeight = FontWeight.ExtraBold
           ))
           Spacer(modifier = Modifier.height(130.dp))
           CreateTappingCircle(value=myMoney,onTapping={myMoney+=1})
       }
   }
}

@Composable
private fun CreateTappingCircle(
    value: Int,
    onTapping: ()-> Unit
) {

    Card(
        backgroundColor = Color.White,
        elevation = 4.dp,
        shape = CircleShape,
        modifier = Modifier.size(135.dp)

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable(onClick = onTapping)
        ) {
            Text(text = "Tap")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CircleClickTheme {
       CircleClick()
    }
}