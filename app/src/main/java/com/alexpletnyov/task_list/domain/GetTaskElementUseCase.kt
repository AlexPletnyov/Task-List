package com.alexpletnyov.task_list.domain

class GetTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	fun getTaskElement(taskElementId: Int): TaskElement {
		return taskListRepository.getTaskElement(taskElementId)
	}
}