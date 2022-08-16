package ru.alexleru.ya.todolist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import ru.alexleru.ya.todolist.R
import ru.alexleru.ya.todolist.domain.PriorityToDo

class AdapterPriority(context: Context) :
    ArrayAdapter<PriorityToDo>(context, 0, PriorityToDo.values()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_priority, parent, false)
        setItem(view, PriorityToDo.values()[position])
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_priority, parent, false)
        setItem(view, PriorityToDo.values()[position])
        return view
    }

    private fun setItem(view: View, priorityToDo: PriorityToDo) {
        val textViewPriority = view.findViewById<TextView>(R.id.textViewPriorityEnum)
        val imageViewPriority = view.findViewById<ImageView>(R.id.imageViewPriorityEnum)
        textViewPriority.text = view.resources.getText(priorityToDo.resName)
        if (priorityToDo.resImage != null) {
            imageViewPriority.setBackgroundResource(priorityToDo.resImage)
            imageViewPriority.visibility = View.VISIBLE
        } else {
            imageViewPriority.visibility = View.INVISIBLE
        }
    }

}