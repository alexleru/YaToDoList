package ru.alexleru.ya.todolist.presentation.fragment

import androidx.recyclerview.widget.DiffUtil
import ru.alexleru.ya.todolist.domain.model.ToDoItem

class ListOfToDoDiffCallback(
    private val oldList: List<ToDoItem>,
    private val newList: List<ToDoItem>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old == new
    }

}