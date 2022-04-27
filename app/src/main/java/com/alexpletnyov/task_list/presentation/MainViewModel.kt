package com.alexpletnyov.task_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alexpletnyov.task_list.data.TaskListRepositoryImpl
import com.alexpletnyov.task_list.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

	private val repository = TaskListRepositoryImpl(application)

	private val getTaskListUseCase = GetTaskListUseCase(repository)
	private val editTaskElementUseCase = EditTaskElementUseCase(repository)
	private val deleteTaskElementUseCase = DeleteTaskElementUseCase(repository)

	var taskList = getTaskListUseCase.getTaskList()

	fun changeCompletedState(taskElement: TaskElement) {
		viewModelScope.launch {
			val newElement = taskElement.copy(completed = !taskElement.completed)
			editTaskElementUseCase.editTaskElement(newElement)
		}
	}

	fun deleteTaskElement(taskElement: TaskElement) {
		viewModelScope.launch {
			deleteTaskElementUseCase.deleteTaskElement(taskElement)
		}
	}
}