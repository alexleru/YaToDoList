package ru.alexleru.ya.todolist.presentation.fragment

import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import ru.alexleru.ya.todolist.domain.model.PriorityToDo
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.toStringDateForForm

@BindingAdapter("priority")
fun bindPriority(spinner: Spinner, priority: PriorityToDo) {
    spinner.setSelection(PriorityToDo.values().indexOfFirst { it == priority })
}

@BindingAdapter("dateDeadLine")
fun bindDateDeadLine(textView: TextView, toDoItem: ToDoItem) {
    toDoItem.deadline?.let { textView.text = it.toStringDateForForm() }
}

@BindingAdapter("switcher")
fun bindSwitcher(switchCompat: SwitchCompat, boolean: Boolean){
    switchCompat.isChecked = boolean
}