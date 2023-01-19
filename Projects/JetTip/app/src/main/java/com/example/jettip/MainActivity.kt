package com.example.jettip

import android.os.Bundle
import android.text.TextUtils.substring
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettip.components.InputField
import com.example.jettip.ui.theme.JetTipTheme
import com.example.jettip.utils.calTotalPerPerson
import com.example.jettip.utils.calcTip
import com.example.jettip.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipTheme {
                JetTipApp()
            }
        }
    }
}

@Composable
fun JetTipApp() {
    //這邊要在包一個COlumn 且要padding和spaceBy去分隔裡面的原件
    //不然這邊可沒有向web那樣 一個會自動疊一個喔!全部都會擠在最上面:)
    //必須要用spaceBy去作為margin 分隔上下元件
    //padding則是內邊距
    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {

//    TopHeader()
    MainContent()
    }
}

@Composable
fun TopHeader(totalPerPerson:Double) {
    val total = "%.2f".format(totalPerPerson)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(12.dp)),
        color = Color(0x4603D9C4)
        //如果在modifier用background去設定surface顏色
        //那個顏色會被子元件給覆蓋掉喔!
        //如果是要顯示"Surface"的"顏色"，請單獨設定"color"屬性!!!!

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="Total Per Person",
                style = MaterialTheme.typography.h5
            )
            Text(text ="$$total",style=MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold )
        }
    }
}

@Composable
fun MainContent() {

    BillForm()
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier:Modifier = Modifier,
    range :IntRange = 1..100,
) {
    //老師在這是把onValChange當作參數傳進來，但這樣也太多層了吧!
    var amountInput by remember { mutableStateOf("") }

    //沒輸入的時候用的
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var sliderState by remember { mutableStateOf(0f) }
    var tipPercentage = sliderState.toInt()
    //沒輸入的時候用的
    val tipPercent  = tipPercentage


    var splitVal by remember { mutableStateOf(1) }
    var tipValue by remember { mutableStateOf("0") }

    //最終每人要付的tip
    var finalEach by remember { mutableStateOf(0.0) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val inputIsEmpty = amountInput.trim().isEmpty()

    //老師在這有用remember，記錄一下(內容是我的版本，老師是用.value寫法)
    val validState = remember(amountInput) {
        amountInput.trim().isEmpty()
    }
    TopHeader(finalEach)
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()

        ,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp,Color.LightGray)

    ) {

    Column(modifier = Modifier.padding(20.dp)) {
        InputField(
            label = "Enter Bill",
            isSingleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ) ,
            keyboardActions = KeyboardActions {
                //如果是空的，那按下NExt不會有任何效果
                if(inputIsEmpty) return@KeyboardActions
                //反之，可以把鍵盤關閉
                keyboardController?.hide()
                //else focusManager.moveFocus(FocusDirection.Down)

                                              },
            value = amountInput,
            onValueChange = {amountInput = it}
        )
        if(!inputIsEmpty) {
            Column(
                modifier = Modifier.padding(vertical = 18.dp)
            ) {
                SplitPersonSection(
                    splitVal = splitVal,
                    updatePerPay = { finalEach = countEachPay(calcTip(totalBill =amount,tipPercentage = tipPercent ),amountInput,splitVal)},
                    onAdd = {if(splitVal <range.last) splitVal+=1},
                    onSubtract = { splitVal = if(splitVal > 1) splitVal-1 else 1  }
                )
                Row{
                    Text(modifier =Modifier.weight(1f),text = "Tip")
                    Text(modifier = Modifier.padding(end = 8.dp),
                            text = tipValue
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(top = 60.dp)
                ) {
                    Text(text = "$tipPercentage %", fontSize = 20.sp,fontWeight = FontWeight.ExtraBold)
                    Slider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        steps = 3,
                        valueRange = 0f..100f,
                        //他的step是依照valueRange去分的喔!
                        //詳細我懶得算，就是最後'值'當然是照著valueRange去劃分每個step
                        // 但重點是，，他劃分的方式不是100/4=25->每個step = 25%這樣
                        //不是喔!!!! 他是你指定多少step，還要加上頭尾的2個step的%數
                        //所以假設steps = 4，最後就會有6個點，每個part也不會是25%(因為實際是6個step)
                        //如果你要每個part是25%，那step要設定成3!因為0的part不算
                        // 剩下就有4個step，最後每個part就會試25了!!!

                        onValueChangeFinished = {
                            // launch some business logic update with the state you hold
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        //改slider顏色!
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colors.secondary,
                            activeTrackColor = MaterialTheme.colors.secondary
                        ),
                        value = sliderState,
                        onValueChange = {
                            sliderState = it
                            Log.d("Input",amountInput)
                            tipValue = calcTip(totalBill =amount,tipPercentage = tipPercent )
                           finalEach = countEachPay(calcTip(totalBill =amount,tipPercentage = tipPercent ),amountInput,splitVal)
                        }

                    )
                }
            }

        }
    }
    }
}
fun countEachPay(all:String,input:String,splitBy:Int):Double{
    Log.d("countEach", input.substring(1).toDouble().toString())
//    Log.d("countEach2",all.substring(2))
//    Log.d("countEach","${input.toDouble()}")
    return ((( all.substring(1).toDouble())+ (input.toDouble()))/splitBy)
}
@Composable
fun SplitPersonSection(
    modifier: Modifier = Modifier,
    updatePerPay:()->Unit,
    splitVal:Int = 0,
    onAdd: () -> Unit,
    onSubtract: ()->Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =Arrangement.Center,

    ) {
        Text(
            fontWeight = FontWeight.ExtraBold,
            text = "Split",
            modifier = Modifier.weight(1f))

        RoundIconButton(
            imageVector = Icons.Default.Remove,
            onClick = {
                onSubtract()
                updatePerPay()
            }
        )
        Text(
            text = "$splitVal" ,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 9.dp)
        )
        RoundIconButton(
            imageVector = Icons.Default.Add,
            onClick = {
                onAdd()
                updatePerPay()
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTipTheme {
        JetTipApp()
    }
}