package ru.alexleru.ya.todolist.domain

class GetToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun getByIdToDoItem(id: String): ToDoItem{
        return toDoItemsRepository.getByIdToDoItem(id)
    }
}