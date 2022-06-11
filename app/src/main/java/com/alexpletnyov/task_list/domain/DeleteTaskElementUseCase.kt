package com.alexpletnyov.task_list.domain

import javax.inject.Inject

class DeleteTaskElementUseCase @Inject constructor(
	private val taskListRepository: TaskListRepository
) {

	suspend fun deleteTaskElement(taskElement: TaskElement) {
		taskListRepository.deleteTaskElement(taskElement)
	}
}