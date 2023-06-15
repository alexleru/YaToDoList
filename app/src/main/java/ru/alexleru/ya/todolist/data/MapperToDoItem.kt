package ru.alexleru.ya.todolist.data

import ru.alexleru.ya.todolist.domain.model.PriorityToDo
import ru.alexleru.ya.todolist.domain.model.ToDoItem

class MapperToDoItem {

    fun mapToDoItemEntityToDBModel(toDoItem: ToDoItem) = ToDoItemDBModel(
        id = toDoItem.id,
        name = toDoItem.name,
        priorityToDo = mapPriorityToDoEntityToDBModel(toDoItem.priorityToDo),
        deadline = toDoItem.deadline,
        isDone = toDoItem.isDone,
        creationDate = toDoItem.creationDate,
        modifiedDate = toDoItem.modifiedDate,
    )

    private fun mapPriorityToDoEntityToDBModel(priorityToDo: PriorityToDo): PriorityToDoDBModel {
        return when (priorityToDo) {
            PriorityToDo.NONE -> PriorityToDoDBModel.NONE
            PriorityToDo.LOW -> PriorityToDoDBModel.LOW
            PriorityToDo.HIGH -> PriorityToDoDBModel.HIGH
        }
    }

    fun mapToDoItemDBModelToEntity(toDoItemDBModel: ToDoItemDBModel) = ToDoItem(
        id = toDoItemDBModel.id,
        name = toDoItemDBModel.name,
        priorityToDo = mapPriorityToDoDBModelToEntity(toDoItemDBModel.priorityToDo),
        deadline = toDoItemDBModel.deadline,
        isDone = toDoItemDBModel.isDone,
        creationDate = toDoItemDBModel.creationDate,
        modifiedDate = toDoItemDBModel.modifiedDate,
    )

    private fun mapPriorityToDoDBModelToEntity(priorityToDoDBModel: PriorityToDoDBModel): PriorityToDo {
        return when (priorityToDoDBModel) {
            PriorityToDoDBModel.NONE -> PriorityToDo.NONE
            PriorityToDoDBModel.LOW -> PriorityToDo.LOW
            PriorityToDoDBModel.HIGH -> PriorityToDo.HIGH
        }
    }

    fun mapToDoListDBModelToEntity(toDoListDBModel: List<ToDoItemDBModel>):
            List<ToDoItem> {
        return toDoListDBModel.map { mapToDoItemDBModelToEntity(it) }
    }
}