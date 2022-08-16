package ru.alexleru.ya.todolist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.alexleru.ya.todolist.App
import ru.alexleru.ya.todolist.R
import ru.alexleru.ya.todolist.databinding.FragmentListOfToDoBinding
import ru.alexleru.ya.todolist.domain.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemService
import ru.alexleru.ya.todolist.domain.ToDoListener


class ListOfToDoFragment : Fragment() {

    private lateinit var binding: FragmentListOfToDoBinding
    private lateinit var adapter: AdapterListOfToDo

    private val toDoItemService: ToDoItemService
        get() = (requireActivity().application as App).toDoItemService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfToDoBinding.inflate(inflater, container, false)

        adapter = AdapterListOfToDo()
        binding.recycleView.adapter = adapter
        ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recycleView)
        adapter.clickItemListener = AdapterListOfToDo.onClickItemListener {
            val action = ListOfToDoFragmentDirections.actionListOfToDoFragmentToToDoFormFragment(it)
            this.findNavController().navigate(action)

        }
        toDoItemService.addListener(toDoListener)

        return binding.root
    }

    private val toDoListener: ToDoListener = { adapter.list = it }

    private val simpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val toDo = adapter.list[position]
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        toDoItemService.removeToDo(toDo)
                        snackbar(toDo, position)
                    }
                    ItemTouchHelper.RIGHT -> {
                        toDoItemService.changeDoneStatus(toDo)
                    }
                }
            }

        }

    fun snackbar(toDo: ToDoItem, position: Int) {
        Snackbar.make(binding.recycleView, toDo.name, Snackbar.LENGTH_LONG).setAction(
            getString(R.string.undo)
        ) {
            toDoItemService.setToDo(toDo, position)
        }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        toDoItemService.deleteListener(toDoListener)
    }
}