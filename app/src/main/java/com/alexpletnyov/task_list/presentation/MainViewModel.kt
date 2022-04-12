package com.alexpletnyov.task_list.presentation

import androidx.lifecycle.ViewModel
import com.alexpletnyov.task_list.data.TaskListRepositoryImpl
import com.alexpletnyov.task_list.domain.*

class MainViewModel : ViewModel() {

	private val repository = TaskListRepositoryImpl

	private val getTaskListUseCase = GetTaskListUseCase(repository)
	private val editTaskElementUseCase = EditTaskElementUseCase(repository)
	private val deleteTaskElementUseCase = DeleteTaskElementUseCase(repository)

	var taskList = getTaskListUseCase.getTaskList()

	fun changeCompletedState(taskElement: TaskElement) {
		val newElement = taskElement.copy(completed = !taskElement.completed)
		editTaskElementUseCase.editTaskElement(newElement)
	}

	fun deleteTaskElement(taskElement: TaskElement) {
		deleteTaskElementUseCase.deleteTaskElement(taskElement)
	}
}