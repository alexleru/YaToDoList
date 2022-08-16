package ru.alexleru.ya.todolist.model

import java.util.*

data class ToDoItem(
    val id: String,
    val name: String,
    val priorityToDo: PriorityToDo,
    val deadline: Date?,
    var isDone: Boolean,
    val creationDate: Date,
    val modifiedDate: Date?
)
