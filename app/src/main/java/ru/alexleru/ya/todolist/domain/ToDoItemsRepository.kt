package ru.alexleru.ya.todolist.domain

interface ToDoItemsRepository {
    fun addToDoItem(toDoItem: ToDoItem)

    fun editToDoItem(toDoItem: ToDoItem)

    fun getByIdToDoItem(id: String): ToDoItem

    fun getToDoList(): List<ToDoItem>

    fun removeToDoItem(toDoItem: ToDoItem)
}