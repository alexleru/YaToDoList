package ru.alexleru.ya.todolist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.alexleru.ya.todolist.data.ToDoItemsRepositoryImpl
import ru.alexleru.ya.todolist.domain.usecase.EditToDoItemUseCase
import ru.alexleru.ya.todolist.domain.usecase.GetToDoListUseCase
import ru.alexleru.ya.todolist.domain.usecase.RemoveToDoItemUseCase
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.usecase.AddToDoItemUseCase

class ListOfToDoViewModel : ViewModel() {

    private val repository = ToDoItemsRepositoryImpl

    private val getToDoListUseCase = GetToDoListUseCase(repository)
    private val addToDoItemUseCase = AddToDoItemUseCase(repository)
    private val removeToDoItemUseCase = RemoveToDoItemUseCase(repository)
    private val editToDoItemUseCase = EditToDoItemUseCase(repository)

    val listOfToDo = getToDoListUseCase.getToDoList()

    fun addToDoItem(toDoItem: ToDoItem){
        addToDoItemUseCase.addToDoItem(toDoItem)
    }

    fun removeToDoItem(toDoItem: ToDoItem) {
        removeToDoItemUseCase.removeToDoItem(toDoItem)
    }

    fun editToDoItem(toDoItem: ToDoItem){
        val newItem = toDoItem.copy(isDone = !toDoItem.isDone)
        editToDoItemUseCase.editToDoItem(newItem)
    }
}