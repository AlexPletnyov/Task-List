package com.alexpletnyov.task_list.domain

import javax.inject.Inject

class AddTaskElementUseCase @Inject constructor(
	private val taskListRepository: TaskListRepository
) {

	suspend fun addTaskElement(taskElement: TaskElement) {
		taskListRepository.addTaskElement(taskElement)
	}
}