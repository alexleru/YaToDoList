package ru.alexleru.ya.todolist.presentation.fragment

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import ru.alexleru.ya.todolist.R
import ru.alexleru.ya.todolist.databinding.ItemTodoBinding
import ru.alexleru.ya.todolist.domain.model.PriorityToDo
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.toStringDateForList

class AdapterListOfToDo : ListAdapter<ToDoItem, ToDoViewHolder>(ListOfToDoItemDiffCallback()) {

    var clickItemListener: ((ToDoItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = getItem(position)
        with(holder.binding) {
            checkBox.isChecked = todo.isDone

            if (todo.isDone) {
                textViewItem.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                textViewItem.paintFlags = Paint.ANTI_ALIAS_FLAG
            }
            textViewItem.text = todo.name

            if (todo.deadline != null) {
                textViewDate.visibility = View.VISIBLE
                textViewDate.text = todo.deadline.toStringDateForList();
            } else {
                textViewDate.visibility = View.GONE
            }

            holder.itemView.setOnClickListener { clickItemListener?.invoke(todo) }

            showImage(todo.priorityToDo, imageViewPriority)
        }
    }

    private fun showImage(priorityToDo: PriorityToDo, imageView: ImageView) {
        when (priorityToDo) {
            PriorityToDo.LOW -> {
                imageView.visibility = View.VISIBLE
                imageView.setBackgroundResource(R.drawable.ic_baseline_arrow_downward)
            }
            PriorityToDo.HIGH -> {
                imageView.visibility = View.VISIBLE
                imageView.setBackgroundResource(R.drawable.ic_baseline_arrow_upward)
            }
            PriorityToDo.NONE -> {
                imageView.visibility = View.GONE
            }
        }
    }
}