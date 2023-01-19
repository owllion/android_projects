package com.example.jettodo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.Instant
import java.time.LocalDateTime
import java.util.*
@Entity(tableName = "todos_tbl")
// Marks a class(這邊是data class) as an entity.
// This class will have a mapping SQLite table in the database
data class TodoItem(
    @PrimaryKey
        val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "todo_title")
        val title:String,
    @ColumnInfo(name="todo_description")
        val description: String,
    @ColumnInfo(name="todo_entry_date")
        //val entryDate: LocalDateTime = LocalDateTime.now()
        //改成下面原因: 老師只說上面也行，但下面比較好= =
        val entryDate:Date = Date.from(Instant.now())
        //Date type是java的util的，不是SQL的喔
)

