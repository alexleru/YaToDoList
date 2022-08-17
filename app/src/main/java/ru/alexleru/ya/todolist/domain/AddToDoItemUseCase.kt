package ru.alexleru.ya.todolist.domain

class AddToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun addToDoItem(toDoItem: ToDoItem){
        toDoItemsRepository.addToDoItem(toDoItem)
    }
}