package com.example.jetnote.model

import java.time.LocalDateTime
import java.util.UUID

data class TodoItem(
    val id:UUID = UUID.randomUUID(),
    val title:String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
