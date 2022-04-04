package com.alexpletnyov.task_list.data

import com.alexpletnyov.task_list.domain.TaskElement
import com.alexpletnyov.task_list.domain.TaskListRepository
import java.lang.RuntimeException

object TaskListRepositoryImpl: TaskListRepository {

	private val taskList = mutableListOf<TaskElement>()

	private var autoIncrementId = 0

	override fun addTaskElement(taskElement: TaskElement) {
		if (taskElement.id == TaskElement.UNDEFINED_ID) {
			taskElement.id = autoIncrementId++
		}
		taskList.add(taskElement)
	}

	override fun deleteTaskElement(taskElement: TaskElement) {
		taskList.remove(taskElement)
	}

	override fun editTaskElement(taskElement: TaskElement) {
		val oldElement = getTaskElement(taskElement.id)
		taskList.remove(oldElement)
		addTaskElement(taskElement)
	}

	override fun getTaskElement(taskElementId: Int): TaskElement {
		return taskList.find {
			it.id == taskElementId
		} ?: throw RuntimeException("Element with id $taskElementId not found")
	}

	override fun getTaskList(): List<TaskElement> {
		return taskList.toList()
	}
}