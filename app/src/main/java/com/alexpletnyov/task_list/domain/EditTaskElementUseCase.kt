package com.alexpletnyov.task_list.domain

class EditTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	suspend fun editTaskElement(taskElement: TaskElement) {
		taskListRepository.editTaskElement(taskElement)
	}
}