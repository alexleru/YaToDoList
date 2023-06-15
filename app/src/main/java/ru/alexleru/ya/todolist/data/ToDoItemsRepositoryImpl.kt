package ru.alexleru.ya.todolist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.alexleru.ya.todolist.domain.ToDoItemsRepository
import ru.alexleru.ya.todolist.domain.model.PriorityToDo
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.toDate
import ru.alexleru.ya.todolist.toDateTime
import java.util.UUID

class ToDoItemsRepositoryImpl(application: Application) : ToDoItemsRepository {

    private val dao = AppDatabase.getInstance(application).toDoItemDao()
    private val mapper = MapperToDoItem()

//    private val toDoItemListLV = MutableLiveData<List<ToDoItem>>()
//    private val toDoItemList = sortedSetOf(object : Comparator<ToDoItem> {
//        override fun compare(p0: ToDoItem, p1: ToDoItem): Int {
//            var compareCreationDate = p0.creationDate.compareTo(p1.creationDate)
//            if (compareCreationDate == 0) {
//                compareCreationDate =
//                    if (p0.deadline == null || p1.deadline == null) return 0
//                    else p0.deadline.compareTo(p1.deadline)
//            }
//            return compareCreationDate
//        }
//    })

    override suspend fun addToDoItem(toDoItem: ToDoItem) {
        dao.addToDoItem(mapper.mapToDoItemEntityToDBModel(toDoItem))
    }

    override suspend fun editToDoItem(toDoItem: ToDoItem) {
        dao.addToDoItem(mapper.mapToDoItemEntityToDBModel(toDoItem))
    }

    override suspend fun getByIdToDoItem(id: UUID): ToDoItem {
        return mapper.mapToDoItemDBModelToEntity(dao.getToDoItemByID(id))
    }

    override fun getToDoList(): LiveData<List<ToDoItem>> =
        MediatorLiveData<List<ToDoItem>>().apply {
            addSource(dao.getToDoList()) {
                value = mapper.mapToDoListDBModelToEntity(it)
            }
        }

    override suspend fun removeToDoItem(toDoItem: ToDoItem) {
        dao.deleteToDoItemById(toDoItem.id)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            addToDoItem(
                ToDoItem(
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
                    "Автотесты",
                    PriorityToDo.HIGH,
                    "22/08/2022".toDate(),
                    false,
                    "19/07/2022 19:00:01".toDateTime(),
                    null
                )
            )

            addToDoItem(
                ToDoItem(
                    "Алгоритмы",
                    PriorityToDo.HIGH,
                    "24/08/2022".toDate(),
                    false,
                    "21/08/2022 19:00:01".toDateTime(),
                    null
                )
            )

            addToDoItem(
                ToDoItem(
                    "Accessibility (факультатив)",
                    PriorityToDo.HIGH,
                    "29/08/2022".toDate(),
                    false,
                    "21/08/2022 19:00:01".toDateTime(),
                    null
                )
            )

            addToDoItem(
                ToDoItem(
                    "Инструменты контроля приложений",
                    PriorityToDo.HIGH,
                    "31/08/2022".toDate(),
                    false,
                    "21/08/2022 19:00:01".toDateTime(),
                    null
                )
            )

            addToDoItem(
                ToDoItem(
                    "Про отбор в IT-компанию",
                    PriorityToDo.HIGH,
                    "01/09/2022".toDate(),
                    false,
                    "21/08/2022 19:00:01".toDateTime(),
                    null
                )
            )

            addToDoItem(
                ToDoItem(
                    "Практики разработчиков",
                    PriorityToDo.HIGH,
                    "04/09/2022".toDate(),
                    false,
                    "21/08/2022 19:00:01".toDateTime(),
                    null
                )
            )

            addToDoItem(
                ToDoItem(
                    "Интенсив тренировки по алгоритмам",
                    PriorityToDo.HIGH,
                    "05/09/2022".toDate(),
                    false,
                    "21/08/2022 19:00:01".toDateTime(),
                    null
                )
            )
        }
    }
}