package ru.alexleru.ya.todolist.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.alexleru.ya.todolist.*
import ru.alexleru.ya.todolist.databinding.FragmentToDoFormBinding
import ru.alexleru.ya.todolist.domain.PriorityToDo
import ru.alexleru.ya.todolist.domain.ToDoItem
import ru.alexleru.ya.todolist.domain.ToDoItemService
import java.util.*

class ToDoFormFragment : Fragment() {
    private val arg: ToDoFormFragmentArgs by navArgs()

    private lateinit var binding: FragmentToDoFormBinding
    private lateinit var switch: SwitchCompat
    private lateinit var spinner: Spinner

    private val toDoItemService: ToDoItemService
        get() = (requireActivity().application as App).toDoItemService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentToDoFormBinding.inflate(inflater, container, false)
        view()
        return binding.root
    }

    private fun view() {
        buttonClose()
        buttonSave()
        spinner()
        openToDoItem(arg.id)
        switchView()
        textViewDate()

    }

    private fun buttonClose() {
        binding.imageViewClose.setOnClickListener { activity?.onBackPressed() }
    }

    private fun buttonSave() {

        binding.editTextMultiLine.isHasText {
            binding.buttonSave.isEnabled = it.isNotEmpty()
        }
        binding.buttonSave.setOnClickListener {
            toDoItemService.add(saveToDoItem())
            activity?.onBackPressed()
        }
    }

    private fun spinner() {
        spinner = binding.spinnerPriority
        spinner.adapter = AdapterPriority(this.requireContext())
    }


    private fun textViewDate() {
        binding.textViewDate.setOnClickListener {
            if (!binding.textViewDate.text.isNullOrEmpty())
                viewDatePickerDialog()
        }
    }

    private fun switchView() {
        switch = binding.switchOnDate
        switch.setOnCheckedChangeListener { _, b ->
            if (b) {
                viewDatePickerDialog()
            } else {
                binding.textViewDate.text = null

            }
        }
    }

    private fun openToDoItem(idArg: String) {
        val toDoItem = toDoItemService.getById(idArg)
        val id: String = toDoItem.id
        binding.editTextMultiLine.setText(toDoItem.name)
        spinner.setSelection(PriorityToDo.values().indexOfFirst { it == toDoItem.priorityToDo })
        toDoItem.deadline?.let {
            binding.textViewDate.text = it.toStringDateForForm()
            binding.switchOnDate.isChecked = true
        }
        val isDone: Boolean = toDoItem.isDone
        val creationDate: Date = toDoItem.creationDate
        val modifiedDate: Date? = toDoItem.modifiedDate
    }

    private fun saveToDoItem(): ToDoItem {
        val id: String = toDoItemService.getListOfToDo().maxOf { it.id } + 1
        val name: String = binding.editTextMultiLine.text.toString()
        val priorityToDo: PriorityToDo = spinner.selectedItem as PriorityToDo
        val deadline: Date? =
            if (binding.switchOnDate.isChecked) binding.textViewDate.text.toString()
                .stringFromDatePickerToDateFormat()
            else null
        val isDone = false
        val creationDate = Date()
        val modifiedDate: Date? = null

        return ToDoItem(id, name, priorityToDo, deadline, isDone, creationDate, modifiedDate)
    }

    private fun viewDatePickerDialog() {
        val onDateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                binding.textViewDate.text =
                    stringFromDatePickerToStringFormat("$day/${month + 1}/$year")
            }
        val date = Date()

        val datePickerDialog =
            DatePickerDialog(
                this.requireContext(),
                onDateSetListener,
                dateToTriple(date).first,
                dateToTriple(date).second,
                dateToTriple(date).third
            )
        datePickerDialog.setOnCancelListener {
            if (binding.textViewDate.text.isNullOrEmpty()) {
                switch.isChecked = false
            }
        }
        datePickerDialog.show()
    }
}