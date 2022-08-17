package ru.alexleru.ya.todolist.domain

class GetToDoListUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun getToDoList(): List<ToDoItem>{
        return toDoItemsRepository.getToDoList()
    }
}