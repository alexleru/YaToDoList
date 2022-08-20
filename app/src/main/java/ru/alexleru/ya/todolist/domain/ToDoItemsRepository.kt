package ru.alexleru.ya.todolist.domain

import androidx.lifecycle.LiveData

interface ToDoItemsRepository {
    fun addToDoItem(toDoItem: ToDoItem)

    fun editToDoItem(toDoItem: ToDoItem)

    fun getByIdToDoItem(id: String): ToDoItem

    fun getToDoList(): LiveData<List<ToDoItem>>

    fun removeToDoItem(toDoItem: ToDoItem)
}