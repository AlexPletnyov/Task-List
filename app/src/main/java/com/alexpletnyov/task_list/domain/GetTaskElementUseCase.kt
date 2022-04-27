package com.alexpletnyov.task_list.domain

class GetTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	suspend fun getTaskElement(taskElementId: Int): TaskElement {
		return taskListRepository.getTaskElement(taskElementId)
	}
}