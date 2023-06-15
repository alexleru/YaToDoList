package ru.alexleru.ya.todolist.domain

import androidx.lifecycle.LiveData
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import java.util.UUID

interface ToDoItemsRepository {
    suspend fun addToDoItem(toDoItem: ToDoItem)

    suspend fun editToDoItem(toDoItem: ToDoItem)

    suspend fun getByIdToDoItem(id: UUID): ToDoItem

    fun getToDoList(): LiveData<List<ToDoItem>>

    suspend fun removeToDoItem(toDoItem: ToDoItem)
}