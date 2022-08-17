package ru.alexleru.ya.todolist.domain

import java.util.*

data class ToDoItem(
    var id: String = UNDEFINE_ID,
    val name: String,
    val priorityToDo: PriorityToDo,
    val deadline: Date?,
    var isDone: Boolean,
    val creationDate: Date,
    val modifiedDate: Date?
) {
    companion object{
        const val UNDEFINE_ID = "null"
    }
}
