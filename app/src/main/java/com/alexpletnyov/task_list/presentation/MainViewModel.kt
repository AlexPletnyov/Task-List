package com.alexpletnyov.task_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexpletnyov.task_list.data.TaskListRepositoryImpl
import com.alexpletnyov.task_list.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
	private val getTaskListUseCase: GetTaskListUseCase,
	private val editTaskElementUseCase: EditTaskElementUseCase,
	private val deleteTaskElementUseCase: DeleteTaskElementUseCase
) : ViewModel() {

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