package ru.alexleru.ya.todolist.domain.model

import java.util.*

data class ToDoItem(
    val name: String,
    val priorityToDo: PriorityToDo,
    val deadline: Date?,
    val isDone: Boolean,
    val creationDate: Date,
    val modifiedDate: Date?,
    var id: String = UNDEFINE_ID,
) {
    companion object{
        const val UNDEFINE_ID = "null"
    }
}
