package ru.alexleru.ya.todolist.domain.usecase

import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository

class RemoveToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    suspend fun removeToDoItem(toDoItem: ToDoItem){
        toDoItemsRepository.removeToDoItem(toDoItem)
    }
}