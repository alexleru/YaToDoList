package ru.alexleru.ya.todolist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alexleru.ya.todolist.data.ToDoItemsRepositoryImpl
import ru.alexleru.ya.todolist.domain.EditToDoItemUseCase
import ru.alexleru.ya.todolist.domain.GetToDoListUseCase
import ru.alexleru.ya.todolist.domain.RemoveToDoItemUseCase
import ru.alexleru.ya.todolist.domain.ToDoItem

class ListOfToDoViewModel : ViewModel() {

    private val repository = ToDoItemsRepositoryImpl()

    private val getToDoListUseCase = GetToDoListUseCase(repository)
    private val removeToDoItemUseCase = RemoveToDoItemUseCase(repository)
    private val editToDoItemUseCase = EditToDoItemUseCase(repository)

    val listOfToDo = getToDoListUseCase.getToDoList()

    fun removeToDoItem(toDoItem: ToDoItem) {
        removeToDoItemUseCase.removeToDoItem(toDoItem)
    }

    fun editToDoItem(toDoItem: ToDoItem){
        val newItem = toDoItem.copy(isDone = !toDoItem.isDone)
        editToDoItemUseCase.editToDoItem(toDoItem)
    }
}