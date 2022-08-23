package ru.alexleru.ya.todolist.presentation.fragment

import androidx.recyclerview.widget.DiffUtil
import ru.alexleru.ya.todolist.domain.model.ToDoItem

class ListOfToDoItemDiffCallback : DiffUtil.ItemCallback<ToDoItem>() {
    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem) = oldItem == newItem
}