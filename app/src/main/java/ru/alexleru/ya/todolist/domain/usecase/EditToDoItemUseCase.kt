package ru.alexleru.ya.todolist.domain.usecase

import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository

class EditToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    suspend fun editToDoItem(toDoItem: ToDoItem){
        toDoItemsRepository.editToDoItem(toDoItem)
    }
}