package com.example.jettodo.screens

import androidx.lifecycle.ViewModel
import com.example.jettodo.dataSource.TodoDataSource
import com.example.jettodo.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel:ViewModel() {

//    private val _uiState = MutableStateFlow(TodoUiState())
//    val uiState: StateFlow<TodoUiStat> = _uiState.asStateFlow()
    var todoList = mutableListOf<TodoItem>()
    //因為這個就是一班的class，所以也可以用class的方法那些
    init {
        todoList.addAll(TodoDataSource)
    }
    fun addTodo(todo:TodoItem) { todoList.add(todo) }
    fun removeTodo(todo:TodoItem) { todoList.remove(todo) }
    fun getAllTodos():List<TodoItem> = todoList

}