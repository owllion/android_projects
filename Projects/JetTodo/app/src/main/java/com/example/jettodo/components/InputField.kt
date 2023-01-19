package com.example.jettodo.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit, //it就是傳參數喔!只是省略不寫而已!
    maxLine:Int = 1,
    modifier: Modifier = Modifier
) {

        TextField(
            value = value,
            onValueChange = onValueChange,
            //新的!JetTip專案裡面的Slider的color也是差不多的定義方式
            //都是有用啥xxxxDefault的
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            label={ Text(stringResource(label))},
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier.fillMaxWidth().padding(bottom = 30.dp),


        )
}