package com.alexpletnyov.task_list.domain

class GetTaskListUseCase(private val taskListRepository: TaskListRepository) {

	fun getTaskList(): List<TaskElement> {
		return taskListRepository.getTaskList()
	}
}