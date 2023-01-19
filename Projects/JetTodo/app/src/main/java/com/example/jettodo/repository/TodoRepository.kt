package com.example.jettodo.repository

import com.example.jettodo.dataSource.TodoDatabaseDao
import com.example.jettodo.model.TodoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//Repository簡單來講就是has access to the Dao啦
//所以下面的fn都是用Dao裡面的~(不過repo他本身是optional的啦~可以不用~只用viewModel)
class TodoRepository @Inject constructor(private val todoDatabaseDao: TodoDatabaseDao){
    suspend fun addTodo(todo:TodoItem) = todoDatabaseDao.insert(todo)
    suspend fun  updateTodo(todo:TodoItem) = todoDatabaseDao.update(todo)
    suspend fun deleteTodo(todo:TodoItem) = todoDatabaseDao.delete(todo)
    suspend fun  deleteAllTodos() = todoDatabaseDao.deleteAll()
    fun getAllTodos(): Flow<List<TodoItem>> = //一樣，用flow的話，它本身就是非同步了，所以不用suspend
        todoDatabaseDao
            .getTodos()
            .flowOn(Dispatchers.IO)
            .conflate() //conflate中文: 結合/混合
        //傻眼 完全帶過:)))) 我也不知這些是啥謝謝，他就直接講"好這裡我們要flowOn(這裡面的也完全念過去ㄏ)"
        //conflate是啥? 不知道!
}