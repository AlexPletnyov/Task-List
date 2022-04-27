package com.alexpletnyov.task_list.domain

class AddTaskElementUseCase(private val taskListRepository: TaskListRepository) {

	suspend fun addTaskElement(taskElement: TaskElement) {
		taskListRepository.addTaskElement(taskElement)
	}
}