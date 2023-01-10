package com.example.messagecard

import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagecard.ui.theme.MessageCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MessageCard(Message("Jena", getString(R.string.cool)))
                }
            }

        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {

    Column (
        verticalArrangement = Arrangement.Center,
        ){
            Surface(color= Color.Cyan) {
                Text(
                    text = msg.author,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp),
                    fontSize = 36.sp
                )
            }
            Text(
                text = msg.body,
                fontSize=20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 16.dp,top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),

            )

        Box {
            Image(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                painter =
                painterResource(R.drawable.wednesday),
                contentDescription = "My Wednesday",
                contentScale = ContentScale.Crop
            //加上ContentScale.Crop之前，圖片下方和screen底部還有詭異空隙
            //但加上這個後就完全佔滿了
            )
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("You guys are making me nauseous,", "Not in the good way."))
}