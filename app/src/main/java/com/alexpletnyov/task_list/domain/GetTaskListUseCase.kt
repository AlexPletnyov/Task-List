package com.alexpletnyov.task_list.domain

import androidx.lifecycle.LiveData

class GetTaskListUseCase(private val taskListRepository: TaskListRepository) {

	fun getTaskList(): LiveData<List<TaskElement>> {
		return taskListRepository.getTaskList()
	}
}