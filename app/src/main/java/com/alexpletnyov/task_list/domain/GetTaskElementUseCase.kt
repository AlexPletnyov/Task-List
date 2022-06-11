package com.alexpletnyov.task_list.domain

import javax.inject.Inject

class GetTaskElementUseCase @Inject constructor(
	private val taskListRepository: TaskListRepository
) {

	suspend fun getTaskElement(taskElementId: Int): TaskElement {
		return taskListRepository.getTaskElement(taskElementId)
	}
}