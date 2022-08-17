package ru.alexleru.ya.todolist.domain

class RemoveToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun removeToDoItem(toDoItem: ToDoItem){
        toDoItemsRepository.removeToDoItem(toDoItem)
    }
}