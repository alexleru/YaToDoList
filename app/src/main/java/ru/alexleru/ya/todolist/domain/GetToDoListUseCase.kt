package ru.alexleru.ya.todolist.domain

import androidx.lifecycle.LiveData

class GetToDoListUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun getToDoList(): LiveData<List<ToDoItem>> {
        return toDoItemsRepository.getToDoList()
    }
}