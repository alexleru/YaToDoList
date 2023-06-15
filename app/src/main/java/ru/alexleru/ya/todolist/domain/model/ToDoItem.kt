package ru.alexleru.ya.todolist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ToDoItem(
    val name: String,
    val priorityToDo: PriorityToDo,
    val deadline: Date?,
    val isDone: Boolean,
    val creationDate: Date,
    val modifiedDate: Date?,
    var id: UUID = UUID.randomUUID(),
): Parcelable
