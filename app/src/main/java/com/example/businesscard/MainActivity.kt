package com.example.businesscard

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val expand by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Card(
            //這邊card的padding是p外圍喔!
            modifier = Modifier
                .width(200.dp)
                .height(39.dp)
                .padding(12.dp),
//                .background(color= Color.LightGray),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.height(300.dp)
//                    .background(color = Color.Red)
                //Column的padding是預期的pad內部右邊的喔! 但會讓整個畫面的內容全都往左縮XD
            ) {
                //這邊card和column，兩個設定、width/height都無效欸 0.0
                CreateImageProfile()
                Divider(thickness = 2.dp)
                CreateInfoSection()
                Button(
                    modifier = Modifier.padding(top=15.dp),
                    onClick = { Log.d("MainActivity", "Click btn")}
                ) {
                    Text(text = "Show Portfolio" )
                }
                Content()
            }
        }
    }
}

@Composable
private fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            //這個LazyCol最外層的Box，他的padding倒是很正常的往裡面pad
    ) {
        Surface(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxSize()
                .padding(3.dp),
            shape = RoundedCornerShape(CornerSize(6.dp)),
            border = BorderStroke(2.dp,Color.LightGray)
        ) {
            Portfolio(data = listOf("Candidate 1","Candidate 2","Candidate 3","Candidate 4"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
       items(data) {
            item ->
             Card(modifier = Modifier
                 .fillMaxSize()
                 .padding(5.dp),
                 //Card的padding好像都是pad自己外圍
                 shape = RectangleShape,
                 elevation = 4.dp
             ) {
                 Row(modifier = Modifier
                     .padding(8.dp)//這個是margin，所以是整個row(紫色部分)會被擠壓
                         //周圍會出現空白
                     .background(MaterialTheme.colors.primary)
                     .padding(7.dp),
                    //這個pad內部，白色區塊不會變多，換成紫色區塊擴大
                        verticalAlignment = Alignment.CenterVertically
                 ) {
                     CreateImageProfile(modifier = Modifier.size(100.dp))
                     Column(
                         //這邊用這兩個都沒用喔 藥用alignment!
//                         //verticalArrangement = Arrangement.Center,
//                         //horizontalAlignment = Alignment.CenterHorizontally
//                            modifier = Modifier.align(Alignment.CenterVertically

                            //這個modifier的align,
                            //他 align the element vertically within the Row.
                            //那這是一個方法，直接在Row用verticalAlignment = Alignment.CenterVertically
                            //也ok喔!
                     )
                     {
                         Text(text = item, fontWeight = FontWeight.Bold)
                         Text(text = "A great Project",
                             style = MaterialTheme.typography.body2
                         )
                     }
                 }
             }
        }
    }
}

@Composable
private fun CreateInfoSection() {
    Column(modifier = Modifier.padding(top = 50.dp)) {
        Text(
            text = "Wednesday Adams",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(5.dp),
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "A Killer, A philosopher",
            style = MaterialTheme.typography.subtitle1
        )
        Text(text = "wednesdayAdams@gmail.com", modifier = Modifier.padding(3.dp))


    }
}

@Composable
private fun CreateImageProfile(modifier:Modifier  = Modifier) {
        Surface(
            modifier = modifier
//                        .size(200.dp)
                .padding(12.dp)
            //這個padding很怪 = = 他又是pad外圍了0.0
            //而且不管事pad top 、vertical還是啥得，全部都和pad all是
            //是依樣的結果 = =
//                        .background(color = Color.Magenta)
            ,
            shape = CircleShape,
            border = BorderStroke(5.dp, Color.DarkGray),
            elevation = 4.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = .5f)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp),
                //size2選一寫，可以image自己用modifier 或是外層box/surface的modifier設定size
                //結果是一樣的喔!
                //在image裡面用modifier去padding，會變成照片內部的空間Pad，照片會消失喔
                painter = painterResource(id = R.drawable.wednesday),
                contentDescription = "Wednesday"
            )
        }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        CreateBizCard()
    }
}