package ru.alexleru.ya.todolist.data

import ru.alexleru.ya.todolist.domain.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository

class ToDoItemsRepositoryImpl : ToDoItemsRepository {

    val toDoItemList = mutableListOf<ToDoItem>()

    override fun addToDoItem(toDoItem: ToDoItem) {
        toDoItemList.add(toDoItem)
    }

    override fun editToDoItem(toDoItem: ToDoItem) {
        val item = toDoItemList.find { it.id == toDoItem.id }
            ?: throw RuntimeException("Not found ToDoItem $toDoItem by ID")
        removeToDoItem(toDoItem)
        addToDoItem(toDoItem)
    }

    override fun getByIdToDoItem(id: String): ToDoItem {
        return toDoItemList.find { it.id == id }
            ?: throw RuntimeException("Not found ToDoItem $id by ID")
    }

    override fun getToDoList(): List<ToDoItem> {
        return toDoItemList.toList()
    }

    override fun removeToDoItem(toDoItem: ToDoItem) {
        toDoItemList.remove(toDoItem)
    }

}