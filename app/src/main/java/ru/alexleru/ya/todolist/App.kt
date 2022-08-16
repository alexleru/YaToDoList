package ru.alexleru.ya.todolist

import android.app.Application
import ru.alexleru.ya.todolist.model.ToDoItemService

class App:Application() {
    val toDoItemService = ToDoItemService()
}