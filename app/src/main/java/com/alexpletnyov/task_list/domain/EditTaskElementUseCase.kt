package com.alexpletnyov.task_list.domain

import javax.inject.Inject

class EditTaskElementUseCase @Inject constructor(
	private val taskListRepository: TaskListRepository
) {

	suspend fun editTaskElement(taskElement: TaskElement) {
		taskListRepository.editTaskElement(taskElement)
	}
}