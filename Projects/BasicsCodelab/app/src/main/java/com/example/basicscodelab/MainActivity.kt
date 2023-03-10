package com.example.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                //
                MyApp(modifier = Modifier.fillMaxSize())
//                //way to style Composable functions
//                Surface(
//                // A surface container using the 'background' color from the theme
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("BTS")
//                }
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }

}

@Composable
fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)) {
            Text(text = "Hello")
            Text(text = name)
        }
        //Surface和Text都是Compose UI
    }
}

//用來在Design editor裡面預覽的 不是手機喔! 手機是上面的
@Preview(
    showBackground = true, name = "Text preview",
    widthDp = 320
)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        MyApp()

    }
}