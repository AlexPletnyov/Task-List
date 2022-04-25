package com.alexpletnyov.task_list.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.task_list.R
import com.alexpletnyov.task_list.domain.TaskElement
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class TaskElementActivity : AppCompatActivity() {

	//	private lateinit var viewModel: TaskElementViewModel
//
//	private lateinit var tilName: TextInputLayout
//	private lateinit var tilDescription: TextInputLayout
//	private lateinit var etName: EditText
//	private lateinit var etDescription: EditText
//	private lateinit var buttonSave: Button
//
	private var screenMode = MODE_UNKNOWN
	private var taskElementId = TaskElement.UNDEFINED_ID

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_task_element)
		parseIntent()
//		viewModel = ViewModelProvider(this)[TaskElementViewModel::class.java]
//		initViews()
//		addTextChangeListener()
		launchRightMode()
//		observeViewModel()
	}

	//	private fun observeViewModel() {
//		viewModel.errorInputName.observe(this) {
//			val message = if (it) {
//				getString(R.string.error_input_name)
//			} else {
//				null
//			}
//			tilName.error = message
//		}
//		viewModel.shouldCloseScreen.observe(this) {
//			finish()
//		}
//	}
//
	private fun launchRightMode() {
		val fragment = when (screenMode) {
			MODE_EDIT -> TaskElementFragment.newInstanceEditElement(taskElementId)
			MODE_ADD -> TaskElementFragment.newInstanceAddElement()
			else -> throw RuntimeException("Unknown screen mode $screenMode")
		}
		supportFragmentManager.beginTransaction()
			.add(R.id.task_element_container, fragment)
			.commit()
	}

	//
//	private fun addTextChangeListener() {
//		etName.addTextChangedListener(object : TextWatcher {
//			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//			}
//
//			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//				viewModel.resetErrorInputName()
//			}
//
//			override fun afterTextChanged(p0: Editable?) {
//			}
//		})
//	}
//
//	private fun launchEditMode() {
//		viewModel.getTaskElement(taskElementId)
//		viewModel.taskElement.observe(this) {
//			etName.setText(it.name)
//			etDescription.setText(it.description)
//		}
//		buttonSave.setOnClickListener {
//			viewModel.editTaskElement(etName.text?.toString(), etDescription.text?.toString())
//		}
//	}
//
//	private fun launchAddMode() {
//		buttonSave.setOnClickListener {
//			viewModel.addTaskElement(etName.text?.toString(), etDescription.text?.toString())
//		}
//	}
//
	private fun parseIntent() {
		if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
			throw RuntimeException("Param screen mode is absent")
		}
		val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
		if (mode != MODE_EDIT && mode != MODE_ADD) {
			throw RuntimeException("Unknown screen mode $mode")
		}
		screenMode = mode
		if (screenMode == MODE_EDIT) {
			if (!intent.hasExtra(EXTRA_TASK_ELEMENT_ID)) {
				throw RuntimeException("Param task element id is absent")
			}
			taskElementId = intent.getIntExtra(EXTRA_TASK_ELEMENT_ID, TaskElement.UNDEFINED_ID)
		}
	}

	//
//	private fun initViews() {
//		tilName = findViewById(R.id.til_name)
//		tilDescription = findViewById(R.id.til_description)
//		etName = findViewById(R.id.et_name)
//		etDescription = findViewById(R.id.et_description)
//		buttonSave = findViewById(R.id.save_button)
//	}
//
	companion object {
		private const val EXTRA_SCREEN_MODE = "extra_mode"
		private const val EXTRA_TASK_ELEMENT_ID = "extra_task_element_id"
		private const val MODE_EDIT = "mode_edit"
		private const val MODE_ADD = "mode_add"
		private const val MODE_UNKNOWN = ""

		fun newIntentAddElement(context: Context): Intent {
			val intent = Intent(context, TaskElementActivity::class.java)
			intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
			return intent
		}

		fun newIntentEditElement(context: Context, taskElementId: Int): Intent {
			val intent = Intent(context, TaskElementActivity::class.java)
			intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
			intent.putExtra(EXTRA_TASK_ELEMENT_ID, taskElementId)
			return intent
		}
	}
}