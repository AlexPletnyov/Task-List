package com.alexpletnyov.task_list.domain

class EditTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	fun editTaskElement(taskElement: TaskElement) {
		taskListRepository.editTaskElement(taskElement)
	}
}