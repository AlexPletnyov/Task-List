package com.alexpletnyov.task_list.domain

import androidx.lifecycle.LiveData

interface TaskListRepository {

	fun addTaskElement(taskElement: TaskElement)
	fun deleteTaskElement(taskElement: TaskElement)
	fun editTaskElement(taskElement: TaskElement)
	fun getTaskElement(taskElementId: Int): TaskElement
	fun getTaskList(): LiveData<List<TaskElement>>
}