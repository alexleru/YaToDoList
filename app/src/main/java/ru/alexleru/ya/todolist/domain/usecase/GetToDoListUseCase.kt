package ru.alexleru.ya.todolist.domain.usecase

import androidx.lifecycle.LiveData
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository

class GetToDoListUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun getToDoList(): LiveData<List<ToDoItem>> {
        return toDoItemsRepository.getToDoList()
    }
}