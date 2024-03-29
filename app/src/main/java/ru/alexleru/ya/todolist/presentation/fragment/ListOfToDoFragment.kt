package ru.alexleru.ya.todolist.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.alexleru.ya.todolist.R
import ru.alexleru.ya.todolist.databinding.FragmentListOfToDoBinding
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.presentation.viewmodel.ListOfToDoViewModel

class ListOfToDoFragment : Fragment() {

    private var _binding: FragmentListOfToDoBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentListOfToDoBinding == Null")


    private lateinit var adapter: AdapterListOfToDo
    private val listOfToDoViewModel: ListOfToDoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListOfToDoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        floatButton()
        recycleView()
    }

    private fun recycleView() {
        adapter = AdapterListOfToDo()
        binding.recycleView.adapter = adapter
        listOfToDoViewModel.listOfToDo.observe(viewLifecycleOwner) {
            adapter.submitList(it)

        }
        recycleViewItemTouchHelper()
        recycleClickItem()
    }

    private fun recycleViewItemTouchHelper() {
        ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recycleView)

    }

    private fun recycleClickItem() {
        adapter.clickItemListener = {
            val action =
                ListOfToDoFragmentDirections.actionListOfToDoFragmentToToDoFormFragment(it)
            this.findNavController().navigate(action)
        }
    }

    private fun floatButton() {
        binding.createToDoFloatButton.setOnClickListener {
            val action =
                ListOfToDoFragmentDirections.actionListOfToDoFragmentToToDoFormFragment(null)
            this.findNavController().navigate(action)
        }
    }

    private val simpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val toDoItem = adapter.currentList[position]
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        listOfToDoViewModel.removeToDoItem(toDoItem)
                        snackbar(toDoItem)
                    }

                    ItemTouchHelper.RIGHT -> {
                        listOfToDoViewModel.editToDoItem(toDoItem)
                    }
                }
            }

        }

    fun snackbar(toDoItem: ToDoItem) {
        Snackbar.make(binding.recycleView, toDoItem.name, Snackbar.LENGTH_LONG).setAction(
            getString(R.string.undo)
        ) {
            listOfToDoViewModel.addToDoItem(toDoItem)
        }.show()
    }

}