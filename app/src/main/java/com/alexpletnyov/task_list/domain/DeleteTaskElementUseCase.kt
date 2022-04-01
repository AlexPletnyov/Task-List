package com.alexpletnyov.task_list.domain

class DeleteTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	fun deleteTaskElement(taskElement: TaskElement) {
		taskListRepository.deleteTaskElement(taskElement)
	}
}