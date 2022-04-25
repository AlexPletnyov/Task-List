package com.alexpletnyov.task_list.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.task_list.R
import com.alexpletnyov.task_list.domain.TaskElement
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class TaskElementFragment() : Fragment() {

	private lateinit var viewModel: TaskElementViewModel
	private lateinit var onEditingFinishedListener: OnEditingFinishedListener

	private lateinit var tilName: TextInputLayout
	private lateinit var tilDescription: TextInputLayout
	private lateinit var etName: EditText
	private lateinit var etDescription: EditText
	private lateinit var buttonSave: Button

	private var screenMode: String = MODE_UNKNOWN
	private var taskElementId: Int = TaskElement.UNDEFINED_ID

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnEditingFinishedListener) {
			onEditingFinishedListener = context
		} else {
			throw RuntimeException("Activity must implement OnEditingFinishedListener")
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		parseParams()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_task_element, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel = ViewModelProvider(this)[TaskElementViewModel::class.java]
		initViews(view)
		addTextChangeListener()
		launchRightMode()
		observeViewModel()
	}

	private fun observeViewModel() {
		viewModel.errorInputName.observe(viewLifecycleOwner) {
			val message = if (it) {
				getString(R.string.error_input_name)
			} else {
				null
			}
			tilName.error = message
		}
		viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
			onEditingFinishedListener.onEditingFinished()
		}
	}

	private fun launchRightMode() {
		when (screenMode) {
			MODE_EDIT -> launchEditMode()
			MODE_ADD -> launchAddMode()
		}
	}

	private fun addTextChangeListener() {
		etName.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
				viewModel.resetErrorInputName()
			}

			override fun afterTextChanged(p0: Editable?) {
			}
		})
	}

	private fun launchEditMode() {
		viewModel.getTaskElement(taskElementId)
		viewModel.taskElement.observe(viewLifecycleOwner) {
			etName.setText(it.name)
			etDescription.setText(it.description)
		}
		buttonSave.setOnClickListener {
			viewModel.editTaskElement(etName.text?.toString(), etDescription.text?.toString())
		}
	}

	private fun launchAddMode() {
		buttonSave.setOnClickListener {
			viewModel.addTaskElement(etName.text?.toString(), etDescription.text?.toString())
		}
	}

	private fun parseParams() {
		val args = requireArguments()
		if (!args.containsKey(SCREEN_MODE)) {
			throw RuntimeException("Param screen mode is absent")
		}
		val mode = args.getString(SCREEN_MODE)
		if (mode != MODE_EDIT && mode != MODE_ADD) {
			throw RuntimeException("Unknown screen mode $mode")
		}
		screenMode = mode
		if (screenMode == MODE_EDIT) {
			if (!args.containsKey(TASK_ELEMENT_ID)) {
				throw RuntimeException("Param task element id is absent")
			}
			taskElementId = args.getInt(TASK_ELEMENT_ID, TaskElement.UNDEFINED_ID)
		}
	}

	private fun initViews(view: View) {
		tilName = view.findViewById(R.id.til_name)
		tilDescription = view.findViewById(R.id.til_description)
		etName = view.findViewById(R.id.et_name)
		etDescription = view.findViewById(R.id.et_description)
		buttonSave = view.findViewById(R.id.save_button)
	}

	interface OnEditingFinishedListener {

		fun onEditingFinished()
	}

	companion object {

		private const val SCREEN_MODE = "extra_mode"
		private const val TASK_ELEMENT_ID = "extra_task_element_id"
		private const val MODE_EDIT = "mode_edit"
		private const val MODE_ADD = "mode_add"
		private const val MODE_UNKNOWN = ""

		fun newInstanceAddElement(): TaskElementFragment {
			return TaskElementFragment().apply {
				arguments = Bundle().apply {
					putString(SCREEN_MODE, MODE_ADD)
				}
			}
		}

		fun newInstanceEditElement(taskElementId: Int): TaskElementFragment {
			return TaskElementFragment().apply {
				arguments = Bundle().apply {
					putString(SCREEN_MODE, MODE_EDIT)
					putInt(TASK_ELEMENT_ID, taskElementId)
				}
			}
		}
	}
}