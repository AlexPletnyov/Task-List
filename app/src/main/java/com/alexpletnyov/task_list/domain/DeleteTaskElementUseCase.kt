package com.alexpletnyov.task_list.domain

class DeleteTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	suspend fun deleteTaskElement(taskElement: TaskElement) {
		taskListRepository.deleteTaskElement(taskElement)
	}
}