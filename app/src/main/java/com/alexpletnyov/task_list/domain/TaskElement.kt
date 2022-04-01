package com.alexpletnyov.task_list.domain

data class TaskElement(

	val id: Int,
	val name: String,
	val description: String,
	val completed: Boolean
)
