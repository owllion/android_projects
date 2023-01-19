package com.example.jettodo

import android.os.Bundle
import android.system.Os.remove
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jettodo.dataSource.TodoDataSource
import com.example.jettodo.model.TodoItem
import com.example.jettodo.screens.TodoScreen
import com.example.jettodo.screens.TodoViewModel
import com.example.jettodo.ui.theme.JetTodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//用途-> 把 mainActivity 當成 dependency container啦!
// 然後就可以從這裡取道dependency
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTodoTheme {
                JetTodoApp()
            }
        }
    }
}
@Composable
fun JetTodoApp(todoViewModel: TodoViewModel = viewModel()) {
//    var todos = remember { mutableStateListOf<TodoItem>() }
    val todoList = todoViewModel.getAllTodos()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        TodoScreen(todos = todoList ,
            onRemoveTodo = {todoViewModel.removeTodo(it) },
            onAddTodo = {todoViewModel.addTodo(it)}
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetTodoTheme {
        JetTodoApp()
    }
}