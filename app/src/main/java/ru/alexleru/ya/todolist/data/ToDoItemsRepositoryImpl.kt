package ru.alexleru.ya.todolist.data

import ru.alexleru.ya.todolist.domain.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository
import java.util.*

class ToDoItemsRepositoryImpl : ToDoItemsRepository {

    val toDoItemList = mutableListOf<ToDoItem>()
    private fun createId() = UUID.randomUUID().toString()

    override fun addToDoItem(toDoItem: ToDoItem) {
        if (toDoItem.id != ToDoItem.UNDEFINE_ID) {
            toDoItem.id = createId()
        }
        toDoItemList.add(toDoItem)
    }

    override fun editToDoItem(toDoItem: ToDoItem) {
        val oldItem = getByIdToDoItem(toDoItem.id)
        removeToDoItem(oldItem)
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