package com.alexpletnyov.task_list.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
	private val taskListRepository: TaskListRepository
) {

	fun getTaskList(): LiveData<List<TaskElement>> {
		return taskListRepository.getTaskList()
	}
}