package com.alexpletnyov.task_list.domain

data class TaskElement(

	val name: String,
	val description: String,
	val completed: Boolean,
	var id: Int = UNDEFINED_ID
) {
	companion object {

		const val UNDEFINED_ID = 0
	}
}
