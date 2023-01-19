package com.example.jettodo.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jettodo.model.TodoItem
import com.example.jettodo.utils.Convertor

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
@TypeConverters(Convertor::class)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao(): TodoDatabaseDao
    //dao要另外建立在另個檔案中，因為Room是由3個部分組成:Entity/Dao/Database
    //這三個基本都是各自分開的檔案
}


