package ru.alexleru.ya.todolist.domain

class EditToDoItemUseCase(private val toDoItemsRepository: ToDoItemsRepository) {
    fun editToDoItem(toDoItem: ToDoItem){
        toDoItemsRepository.editToDoItem(toDoItem)
    }
}