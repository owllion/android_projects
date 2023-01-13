package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.data.DataSource
import com.example.tiptime.model.Topic
import com.example.tiptime.ui.theme.TipTimeComposeTheme
import java.text.NumberFormat
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var roundUp by remember { mutableStateOf(false) }
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent  = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount,tipPercent,roundUp)


    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        modifier = Modifier.padding(32.dp)
    ) {
//        Text(
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            fontSize = 24.sp,
//            text = stringResource(id = R.string.calculate_tip)
//        )
//        Spacer(Modifier.height(16.dp))
        EditNumberField(
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                //當使用者按下鍵盤上的「Next」動作按鈕時
                // 系統就會執行 onNext 具名參數的 lambda 運算式。
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                    //moveFocus() 函式會將焦點移往指定方向，
                    // 在本例中為向下移動至「Tip %」文字欄位。
                }
            ),
            value = amountInput,
            onValueChange = {amountInput = it}
        )
        EditNumberField(
            label = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            value = tipInput,
            onValueChange = { tipInput = it}
        )
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = {roundUp = it}
        )
        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.tip_amount,tip),
            /*
             * 會找到該string resource 並把%s位置填入 tip 值(動態):
             * <string name="tip_amount">Tip Amount: %s</string>
             * 透過位置格式設定，您可以在字串中顯示動態內容
             * (This is called positional formatting)
             * */
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight =  FontWeight.Bold
        )
//        TopicGrid()
    }
}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun TopicGrid(modifier: Modifier = Modifier) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = modifier.padding(8.dp)
//    ) {
//        items(DataSource.topics) { topic ->
//            GridCard(topic)
//        }
//    }
//}


@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(elevation = 4.dp) {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageResourceId),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

//@Composable
//fun GridCard(topic:Topic,modifier:Modifier = Modifier) {
//    Card(elevation = 4.dp) {
//        Row {
//            Box {
//               Image(
//                   modifier = modifier
//                       .size(68.dp)
//                       .aspectRatio(1f),
//                   contentScale = ContentScale.Crop,
//                   painter = painterResource(id = topic.imageResourceId),
//                   contentDescription = null
//               )
//       }
//
//       Column() {
//           Text(
//               text = stringResource(id = topic.stringResourceId),
//               style = MaterialTheme.typography.body2,
//               modifier = Modifier.padding(
//                   start = 16.dp,
//                   end = 16.dp,top= 16.dp,bottom = 16.dp
//               )
//           )
//
//           Row(
////               verticalAlignment = Alignment.CenterVertically
//           ) {
//               Icon(
//                   painter = painterResource(id = R.drawable.ic_grain),
//                   contentDescription =null,
//                   modifier = Modifier
//                       .padding(start = 16.dp)
//                       .size(12.dp)
//               )
//               Text(
//                   text = topic.availableCourses.toString(),
//                   style = MaterialTheme.typography.caption,
//                   modifier = Modifier.padding(start = 8.dp)
//               )
//
//           }
//       }
//    } }
//
//}


//This composable is stateless.
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label={ Text(stringResource(label))},
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
        //將文字方塊從多行壓縮成水平捲動的單行

    )
}
@VisibleForTesting
//這會讓方法公開，但向他人說明這僅供測試之用。
internal fun calculateTip (
//private can not be accessed, need to use internal instead.
    amount:Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): String {
    var tip  = tipPercent / 100 * amount
    if(roundUp)
        tip = Math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
    //系統會為您提供數字格式設定工具，以便您將數字格式設為貨幣。
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray
            ),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
//                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipTimeComposeTheme {
        TipTimeScreen()
    }
}