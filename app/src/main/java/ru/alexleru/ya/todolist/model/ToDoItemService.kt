package ru.alexleru.ya.todolist.model

import ru.alexleru.ya.todolist.toDate
import ru.alexleru.ya.todolist.toDateTime

typealias ToDoListener = (listOfToDo: List<ToDoItem>) -> Unit

class ToDoItemService {
    private val listOfToDo = mutableListOf<ToDoItem>()
    private val listeners = mutableSetOf<ToDoListener>()

    fun getListOfToDo(): List<ToDoItem> = listOfToDo

    fun getById(id: String): ToDoItem {
        return listOfToDo.first{it.id == id}
    }

    fun add(toDoItem: ToDoItem) {
        listOfToDo.add(toDoItem)
        notifyChange()
    }

    fun setToDo(toDoItem: ToDoItem, position: Int) {
        listOfToDo.add(position, toDoItem)
        notifyChange()
    }

    fun removeToDo(toDoItem: ToDoItem) {
        val indexToDelete = listOfToDo.indexOfFirst { it.id == toDoItem.id }
        if (indexToDelete != -1) {
            listOfToDo.removeAt(indexToDelete)
            notifyChange()
        }
    }

    fun changeDoneStatus(toDoItem: ToDoItem){
        val indexToDelete = listOfToDo.indexOfFirst { it.id == toDoItem.id }
        if (indexToDelete != -1) {
            listOfToDo.find { it.id == toDoItem.id}?.isDone = !toDoItem.isDone
            notifyChange()
        }
    }

    fun addListener(listener: ToDoListener) {
        listeners.add(listener)
        listener.invoke(listOfToDo)
    }

    fun deleteListener(listener: ToDoListener) {
        listeners.remove(listener)
    }

    private fun notifyChange(){
        listeners.forEach{it.invoke(listOfToDo)}
    }

    init {
        listOfToDo.add(
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
        listOfToDo.add(
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
        listOfToDo.add(
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
        listOfToDo.add(
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
        listOfToDo.add(
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
        listOfToDo.add(
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
        listOfToDo.add(
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
        listOfToDo.add(
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

        listOfToDo.add(
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

        listOfToDo.add(
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

        listOfToDo.add(
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

        listOfToDo.add(
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

        listOfToDo.add(
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