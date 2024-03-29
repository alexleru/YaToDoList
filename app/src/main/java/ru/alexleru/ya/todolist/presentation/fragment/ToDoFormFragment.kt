package ru.alexleru.ya.todolist.presentation.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.alexleru.ya.todolist.databinding.FragmentToDoFormBinding
import ru.alexleru.ya.todolist.dateToTriple
import ru.alexleru.ya.todolist.domain.model.PriorityToDo
import ru.alexleru.ya.todolist.domain.model.ToDoItem
import ru.alexleru.ya.todolist.isHasText
import ru.alexleru.ya.todolist.presentation.viewmodel.ToDoFormViewModel
import ru.alexleru.ya.todolist.stringFromDatePickerToDateFormat
import ru.alexleru.ya.todolist.stringFromDatePickerToStringFormat
import java.util.Date

class ToDoFormFragment : Fragment() {
    private val arg: ToDoFormFragmentArgs by navArgs()
    private val toDoFormViewModel: ToDoFormViewModel by lazy {
        ViewModelProvider(this)[ToDoFormViewModel::class.java]
    }

    private var _binding: FragmentToDoFormBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentToDoFormBinding == null")
    private lateinit var switch: SwitchCompat
    private lateinit var spinner: Spinner
    private lateinit var toDoItem: ToDoItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentToDoFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = toDoFormViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
    }

    private fun initViews() {
        buttonClose()
        buttonSave()
        buttonRemove()
        spinner()
        arg.id?.let { openToDoItem(it) }
        switchView()
        textViewDate()
    }

    private fun buttonRemove() {
        binding.imageButtonDelete.setOnClickListener {
            toDoFormViewModel.removeToDoItem(toDoItem)
        }
    }

    private fun buttonClose() {
        binding.imageViewClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun buttonSave() {
        binding.editTextMultiLine.isHasText {
            binding.buttonSave.isEnabled = it.isNotBlank()
        }
        binding.buttonSave.setOnClickListener {
            if (arg.id == null) {
                addToDoItem()
            } else {
                editToDoItem()
            }
            toDoFormViewModel.canCloseTheFragment.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }

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

    private fun openToDoItem(toDoItem: ToDoItem) {
        toDoFormViewModel.getToDoItemUseCase(toDoItem.id)
        toDoFormViewModel.toDoItem.observe(viewLifecycleOwner) { item ->
            this.toDoItem = item
            //binding.editTextMultiLine.setText(toDoItem.name)
            //spinner.setSelection(PriorityToDo.values().indexOfFirst { it == toDoItem.priorityToDo })
//            toDoItem.deadline?.let {
//                binding.textViewDate.text = it.toStringDateForForm()
//                binding.switchOnDate.isChecked = true
//            }

        }
    }

    private fun addToDoItem() {
        val name: String = binding.editTextMultiLine.text.toString()
        val priorityToDo: PriorityToDo = spinner.selectedItem as PriorityToDo
        val deadline: Date? =
            if (binding.switchOnDate.isChecked) binding.textViewDate.text.toString()
                .stringFromDatePickerToDateFormat()
            else null
        val isDone = false
        val creationDate = Date()
        val modifiedDate: Date? = null
        toDoFormViewModel.addToDoItem(
            name,
            priorityToDo,
            deadline,
            isDone,
            creationDate,
            modifiedDate
        )
    }

    private fun editToDoItem() {
        val name: String = binding.editTextMultiLine.text.toString()
        val priorityToDo: PriorityToDo = spinner.selectedItem as PriorityToDo
        val deadline: Date? =
            if (binding.switchOnDate.isChecked) {
                binding.textViewDate.text.toString().stringFromDatePickerToDateFormat()
            } else {
                null
            }
        toDoFormViewModel.editToDoItem(
            name,
            priorityToDo,
            deadline
        )
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