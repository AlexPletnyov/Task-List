package com.alexpletnyov.task_list.domain

import androidx.lifecycle.LiveData

interface TaskListRepository {

	suspend fun addTaskElement(taskElement: TaskElement)
	suspend fun deleteTaskElement(taskElement: TaskElement)
	suspend fun editTaskElement(taskElement: TaskElement)
	suspend fun getTaskElement(taskElementId: Int): TaskElement
	fun getTaskList(): LiveData<List<TaskElement>>
}