package ru.alexleru.ya.todolist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexleru.ya.todolist.data.ToDoItemsRepositoryImpl
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.usecase.AddToDoItemUseCase
import ru.alexleru.ya.todolist.domain.usecase.EditToDoItemUseCase
import ru.alexleru.ya.todolist.domain.usecase.GetToDoListUseCase
import ru.alexleru.ya.todolist.domain.usecase.RemoveToDoItemUseCase

class ListOfToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ToDoItemsRepositoryImpl(application)

    private val getToDoListUseCase = GetToDoListUseCase(repository)
    private val addToDoItemUseCase = AddToDoItemUseCase(repository)
    private val removeToDoItemUseCase = RemoveToDoItemUseCase(repository)
    private val editToDoItemUseCase = EditToDoItemUseCase(repository)

    val listOfToDo = getToDoListUseCase.getToDoList()

    fun addToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            addToDoItemUseCase.addToDoItem(toDoItem)
        }
    }

    fun removeToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            removeToDoItemUseCase.removeToDoItem(toDoItem)
        }
    }

    fun editToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            val newItem = toDoItem.copy(isDone = !toDoItem.isDone)
            editToDoItemUseCase.editToDoItem(newItem)
        }
    }
}