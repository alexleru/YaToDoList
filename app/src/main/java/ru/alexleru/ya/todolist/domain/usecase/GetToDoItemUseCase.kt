package ru.alexleru.ya.todolist.domain.usecase

import ru.alexleru.ya.todolist.domain.ToDoItemsRepository
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import java.util.UUID

class GetToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    suspend fun getByIdToDoItem(id: UUID): ToDoItem {
        return toDoItemsRepository.getByIdToDoItem(id)
    }
}