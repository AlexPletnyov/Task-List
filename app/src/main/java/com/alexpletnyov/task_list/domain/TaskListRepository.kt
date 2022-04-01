package com.alexpletnyov.task_list.domain

interface TaskListRepository {

	fun addTaskElement(taskElement: TaskElement)
	fun deleteTaskElement(taskElement: TaskElement)
	fun editTaskElement(taskElement: TaskElement)
	fun getTaskElement(taskElementId: Int): TaskElement
	fun getTaskList(): List<TaskElement>
}