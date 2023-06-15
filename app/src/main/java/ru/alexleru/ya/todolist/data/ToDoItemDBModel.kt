package ru.alexleru.ya.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "TODOITEM")
data class ToDoItemDBModel(
    @PrimaryKey(autoGenerate = false)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val priorityToDo: PriorityToDoDBModel,
    val deadline: Date?,
    val isDone: Boolean,
    val creationDate: Date,
    val modifiedDate: Date?,
)
