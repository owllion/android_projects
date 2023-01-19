package com.example.jettodo.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import com.example.jettodo.R
import com.example.jettodo.components.InputField
import com.example.jettodo.components.SaveButton
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettodo.components.TodoRow
import com.example.jettodo.dataSource.TodoDataSource
import com.example.jettodo.model.TodoItem


@Composable
fun TodoScreen(
    todos:List<TodoItem>,
    onAddTodo: (TodoItem) -> Unit,
    onRemoveTodo:(TodoItem) ->Unit
) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current
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
                text = "Add the To-do",
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
                value = description ,
                onValueChange = {
                    if(it.all { char -> char.isLetter() || char.isWhitespace()
                    }) description = it}
            )
            SaveButton(
                onClicked = {
                       onAddTodo(
                           TodoItem(
                               title = title,
                               description = description
                           )
                       )
                    title = ""
                    description = ""
                    Toast.makeText(context,"Todo added!", Toast.LENGTH_SHORT).show()
                },
                title = "Save",
                enabled = title.isNotEmpty() &&  description.isNotEmpty()
            )

        }
        Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
            Divider(modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxWidth())
            Text(
                text = "To-do List",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LazyColumn {
                items(todos) {
                        todo-> TodoRow(
                    todo = todo,
                    onTodoClicked = {onRemoveTodo(todo)} )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoScreen(todos = emptyList(), onAddTodo = {}, onRemoveTodo = {})
}