package com.example.jetnote.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnote.R
import com.example.jetnote.components.InputField
import com.example.jetnote.components.SaveButton

@Composable
fun NoteScreen() {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(10.dp)) {
       TopAppBar(
           title = { 
               Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(
                        color = Color.White, 
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
               )},
           backgroundColor = Color(0xB0CDB90A),
           elevation = 0.dp,
           actions = {
           Icon(imageVector = Icons.Rounded.Notifications,
               contentDescription ="Notification" )
       })
        //Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .padding(top = 40.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your To-do",
                style= MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
            InputField(
                label = R.string.title,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                value = title,
                onValueChange = {
                    if(it.all {
                            char ->
                                char.isLetter() || char.isWhitespace()
                    }) title = it}
            )
            InputField(
                label = R.string.content,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                value = content ,
                onValueChange = {
                    if(it.all {
                    char -> char.isLetter() || char.isWhitespace()
                    }) content = it}
            )
            SaveButton(
                onClicked = { /*TODO*/ },
                title = "Save",
                enabled = title.isNotEmpty() &&  content.isNotEmpty()
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun  NoteScreenPreview() {
    NoteScreen()
}