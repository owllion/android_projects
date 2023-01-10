package com.example.lemonade

import android.os.Bundle
import android.view.Surface
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeGame()
            }
        }
    }
}
@Preview
@Composable
fun LemonadeGame() {
    var currentStep by remember { mutableStateOf(1) }

    // Number of times the lemon needs to be squeezed
    // to turn into a glass of lemonade
    var squeezeCount by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color =MaterialTheme.colors.background
    ) {
        when(currentStep) {
            1 -> LemonadeWithTextAndImg(
                textResourceId = R.string.step_1,
                imgResourceId = R.drawable.lemon_tree,
                contentDescResourceId = R.string.lemon_tree_content_description,
                onImageClick = {
                    // Update to next step
                    currentStep = 2
                    /**
                     *  Each time a lemon is picked from the tree, get a new random number
                     *  between 2 and 4 (inclusive) for the number of times the lemon needs
                     *  to be squeezed to turn into lemonade
                    */
                    //TODO To be brief : generate required squeeze times when you pick a lemon.
                    squeezeCount = (2..4).random()
                }
            )
            2 -> LemonadeWithTextAndImg(
                textResourceId = R.string.step_2,
                imgResourceId = R.drawable.lemon_squeeze,
                contentDescResourceId = R.string.lemon_content_description,
                onImageClick = {
                    squeezeCount-=1
                    if (squeezeCount==0) currentStep = 3
                }
            )
            3 -> LemonadeWithTextAndImg(
                textResourceId = R.string.step_3,
                imgResourceId = R.drawable.lemon_drink,
                contentDescResourceId = R.string.glass_of_lemonade_content_description,
                onImageClick = { currentStep = 4 }
            )
            4 -> LemonadeWithTextAndImg(
                textResourceId = R.string.step_4,
                imgResourceId = R.drawable.lemon_restart,
                contentDescResourceId = R.string.empty_glass_content_description,
                onImageClick = {
                    //Back to starting step
                    currentStep = 1
                }
            )
        }

        }


}

@Composable
fun LemonadeWithTextAndImg(
    modifier:Modifier = Modifier,
    textResourceId: Int,
    contentDescResourceId:Int,
    imgResourceId: Int,
    onImageClick: ()-> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(id = R.string.step_1), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
            Image(
                modifier = Modifier
                    .border(
//                        width = 2.5.dp,
//                        color = Color(0xFF56CAD5),
                        BorderStroke(2.dp, Color(105, 205, 216)),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
                    .clickable ( onClick = onImageClick )
                ,
                painter = painterResource(imgResourceId) ,
                contentDescription = stringResource(id = R.string.lemon_tree_content_description)
            )

    }
}
