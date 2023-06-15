package ru.alexleru.ya.todolist.domain.usecase

import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository

class AddToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    suspend fun addToDoItem(toDoItem: ToDoItem){
        toDoItemsRepository.addToDoItem(toDoItem)
    }
}