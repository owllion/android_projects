package com.example.jettip.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    modifier:Modifier = Modifier,
    label: String,
    isSingleLine:Boolean,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    value: String,
    onValueChange: (String) -> Unit,
) {
OutlinedTextField(
    value = value,
    onValueChange =onValueChange,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney,
        contentDescription ="Money Icon" )},
    singleLine = isSingleLine,
    textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
    label = { Text(text = label)},
    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)

)

}