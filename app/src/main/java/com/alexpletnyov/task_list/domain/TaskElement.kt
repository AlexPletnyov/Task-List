package com.alexpletnyov.task_list.domain

data class TaskElement(

	val name: String,
	val description: String,
	val isCompleted: Boolean,
	var id: Int = UNDEFINED_ID
) {
	companion object {

		const val UNDEFINED_ID = -1
	}
}
