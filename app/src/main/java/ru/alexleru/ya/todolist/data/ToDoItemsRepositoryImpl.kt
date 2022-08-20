package ru.alexleru.ya.todolist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.alexleru.ya.todolist.dateToTriple
import ru.alexleru.ya.todolist.domain.PriorityToDo
import ru.alexleru.ya.todolist.domain.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository
import ru.alexleru.ya.todolist.toDate
import ru.alexleru.ya.todolist.toDateTime
import java.util.*

class ToDoItemsRepositoryImpl : ToDoItemsRepository {

    private val toDoItemListLV = MutableLiveData<List<ToDoItem>>()
    private val toDoItemList = mutableListOf<ToDoItem>()
    private fun createId() = UUID.randomUUID().toString()

    override fun addToDoItem(toDoItem: ToDoItem) {
        if (toDoItem.id != ToDoItem.UNDEFINE_ID) {
            toDoItem.id = createId()
        }
        toDoItemList.add(toDoItem)
        updateToDoItemList()
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

    override fun getToDoList(): LiveData<List<ToDoItem>> {
        return toDoItemListLV
    }

    override fun removeToDoItem(toDoItem: ToDoItem) {
        toDoItemList.remove(toDoItem)
        updateToDoItemList()
    }

    private fun updateToDoItemList(){
        toDoItemListLV.value = toDoItemList.toList()
    }

    init {
        addToDoItem(
            ToDoItem(
                "1",
                "Открытие школ и лектория",
                PriorityToDo.NONE,
                "20/07/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
        addToDoItem(
            ToDoItem(
                "2",
                "Вводная про платформу",
                PriorityToDo.HIGH,
                "22/07/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
        addToDoItem(
            ToDoItem(
                "3",
                "Открытие школ и лектория",
                PriorityToDo.LOW,
                "23/07/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                "22/07/2022 08:04:01".toDateTime()
            )
        )
        addToDoItem(
            ToDoItem(
                "4",
                "Kotlin",
                PriorityToDo.HIGH,
                "25/07/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
        addToDoItem(
            ToDoItem(
                "5",
                "View в Android",
                PriorityToDo.HIGH,
                "27/07/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
        addToDoItem(
            ToDoItem(
                "6",
                "Потоки и асинхронность",
                PriorityToDo.HIGH,
                "01/08/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
        addToDoItem(
            ToDoItem(
                "7",
                "Network в Android",
                PriorityToDo.HIGH,
                "03/08/2022".toDate(),
                true,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
        addToDoItem(
            ToDoItem(
                "8",
                "Архитектура",
                PriorityToDo.HIGH,
                "08/08/2022".toDate(),
                false,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )

        addToDoItem(
            ToDoItem(
                "9",
                "DI",
                PriorityToDo.HIGH,
                "10/08/2022".toDate(),
                false,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )

        addToDoItem(
            ToDoItem(
                "10",
                "Хранение данных",
                PriorityToDo.HIGH,
                "15/08/2022".toDate(),
                false,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )

        addToDoItem(
            ToDoItem(
                "11",
                "Продвинутый UI",
                PriorityToDo.HIGH,
                "17/08/2022".toDate(),
                false,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )

        addToDoItem(
            ToDoItem(
                "12",
                "Jetpack Compose (факультатив)",
                PriorityToDo.HIGH,
                "19/08/2022".toDate(),
                false,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )

        addToDoItem(
            ToDoItem(
                "13",
                "Автотесты",
                PriorityToDo.HIGH,
                "22/08/2022".toDate(),
                false,
                "19/07/2022 19:00:01".toDateTime(),
                null
            )
        )
    }

}