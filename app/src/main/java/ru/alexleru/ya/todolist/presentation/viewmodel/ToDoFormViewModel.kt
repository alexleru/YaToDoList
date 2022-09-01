package ru.alexleru.ya.todolist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alexleru.ya.todolist.data.ToDoItemsRepositoryImpl
import ru.alexleru.ya.todolist.domain.model.PriorityToDo
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.domain.usecase.AddToDoItemUseCase
import ru.alexleru.ya.todolist.domain.usecase.EditToDoItemUseCase
import ru.alexleru.ya.todolist.domain.usecase.GetToDoItemUseCase
import ru.alexleru.ya.todolist.domain.usecase.RemoveToDoItemUseCase
import java.util.*

class ToDoFormViewModel : ViewModel() {

    private val toDoItemsRepository = ToDoItemsRepositoryImpl

    private val addToDoItemUseCase = AddToDoItemUseCase(toDoItemsRepository)
    private val editToDoItemUseCase = EditToDoItemUseCase(toDoItemsRepository)
    private val removeToDoItemUseCase = RemoveToDoItemUseCase(toDoItemsRepository)
    private val getToDoItemUseCase = GetToDoItemUseCase(toDoItemsRepository)

    private val _errorNameInput = MutableLiveData<Boolean>()
    val errorNameInput: LiveData<Boolean>
        get() = _errorNameInput

    private val _canCloseTheFragment = MutableLiveData<Unit>()
    val canCloseTheFragment: LiveData<Unit>
        get() = _canCloseTheFragment

    private val _toDoItem = MutableLiveData<ToDoItem>()
    val toDoItem: LiveData<ToDoItem>
        get() = _toDoItem

    fun addToDoItem(
        name: String,
        priorityToDo: PriorityToDo,
        deadline: Date?,
        isDone: Boolean,
        creationDate: Date,
        modifiedDate: Date?,
    ) {
        val validateName = validateName(name)
        if (validateName) {
            val toDoItem =
                ToDoItem(name, priorityToDo, deadline, isDone, creationDate, modifiedDate)
            addToDoItemUseCase.addToDoItem(toDoItem)
            canCloseTheFragment()
        }
    }

    fun editToDoItem(
        name: String,
        priorityToDo: PriorityToDo,
        deadline: Date?
    ) {
        val validateName = validateName(name)
        if (validateName) {
            _toDoItem.value?.let {
                val item = it.copy(name = name,
                    priorityToDo = priorityToDo,
                    deadline = deadline,
                    modifiedDate = Date())
                editToDoItemUseCase.editToDoItem(item)
                canCloseTheFragment()
            }
        }
    }

    private fun canCloseTheFragment() {
        _canCloseTheFragment.value = Unit
    }

    private fun validateName(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorNameInput.value = result
        }
        return result
    }

    fun resetErrorNameInput() {
        _errorNameInput.value = false
    }

    fun removeToDoItem(toDoItem: ToDoItem) {
        removeToDoItemUseCase.removeToDoItem(toDoItem)
    }

    fun getToDoItemUseCase(id: String) {
        val item = getToDoItemUseCase.getByIdToDoItem(id)
        _toDoItem.value = item
    }
}