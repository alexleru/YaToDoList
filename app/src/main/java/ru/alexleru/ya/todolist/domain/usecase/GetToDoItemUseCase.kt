package ru.alexleru.ya.todolist.domain.usecase

import androidx.lifecycle.LiveData
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository

class GetToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun getByIdToDoItem(id: String): ToDoItem {
        return toDoItemsRepository.getByIdToDoItem(id)
    }
}