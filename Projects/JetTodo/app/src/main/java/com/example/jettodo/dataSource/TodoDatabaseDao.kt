package com.example.jettodo.dataSource

import androidx.room.*
import com.example.jettodo.model.TodoItem
import kotlinx.coroutines.flow.Flow

//dao是interface(他是和SQL溝通的介面(interface))
@Dao
interface TodoDatabaseDao {

    @Query("SELECT * FROM todos_tbl")
    fun getTodos(): Flow<List<TodoItem>> //->這裡從List<>改成Flow<>，這樣才會是非同步的->才不會這邊有問題
    //而導致block UI 的composition !
    //其餘的地方是用suspend達到非同步
    //---------------------------------
    //翻譯: 當getTodos被呼叫，其實是去直營Query後面的SQL指令
    //所以fn的type才會是List<TodoItem>
    //符合文章所說:CRUD都以Decorator來完成~

    @Query("SELECT * from todos_tbl where id=:id")
    suspend fun getTodoById(id:String):TodoItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo:TodoItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(todo:TodoItem)

    @Query("DELETE from todos_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(item:TodoItem)
}
