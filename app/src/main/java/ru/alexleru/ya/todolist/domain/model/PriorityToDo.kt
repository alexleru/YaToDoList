package ru.alexleru.ya.todolist.domain.model

import ru.alexleru.ya.todolist.R


enum class PriorityToDo(val resName: Int, val resImage: Int?) {
    NONE(R.string.priority_none, null),
    LOW(R.string.priority_low, R.drawable.ic_baseline_arrow_downward),
    HIGH(R.string.priority_high, R.drawable.ic_baseline_arrow_upward)

}