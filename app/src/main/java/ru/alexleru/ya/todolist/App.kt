package ru.alexleru.ya.todolist

import android.app.Application
import ru.alexleru.ya.todolist.domain.ToDoItemService

class App:Application() {
    val toDoItemService = ToDoItemService()
}