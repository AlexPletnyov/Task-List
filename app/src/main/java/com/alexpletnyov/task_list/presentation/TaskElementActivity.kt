package com.alexpletnyov.task_list.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alexpletnyov.task_list.R
import com.alexpletnyov.task_list.domain.TaskElement
import java.lang.RuntimeException

class TaskElementActivity : AppCompatActivity(), TaskElementFragment.OnEditingFinishedListener {

	private var screenMode = MODE_UNKNOWN
	private var taskElementId = TaskElement.UNDEFINED_ID

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_task_element)
		parseIntent()
		if (savedInstanceState == null) {
			launchRightMode()
		}
	}

	override fun onEditingFinished() {
		Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
		finish()
	}

	private fun launchRightMode() {
		val fragment = when (screenMode) {
			MODE_EDIT -> TaskElementFragment.newInstanceEditElement(taskElementId)
			MODE_ADD -> TaskElementFragment.newInstanceAddElement()
			else -> throw RuntimeException("Unknown screen mode $screenMode")
		}
		supportFragmentManager.beginTransaction()
			.replace(R.id.task_element_container, fragment)
			.commit()
	}

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