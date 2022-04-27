package com.alexpletnyov.task_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alexpletnyov.task_list.data.TaskListRepositoryImpl
import com.alexpletnyov.task_list.domain.AddTaskElementUseCase
import com.alexpletnyov.task_list.domain.EditTaskElementUseCase
import com.alexpletnyov.task_list.domain.GetTaskElementUseCase
import com.alexpletnyov.task_list.domain.TaskElement
import kotlinx.coroutines.launch

class TaskElementViewModel(application: Application) : AndroidViewModel(application) {

	private val repository = TaskListRepositoryImpl(application)

	private val getTaskElementUseCase = GetTaskElementUseCase(repository)
	private val addTaskElementUseCase = AddTaskElementUseCase(repository)
	private val editTaskElementUseCase = EditTaskElementUseCase(repository)

	private val _errorInputName = MutableLiveData<Boolean>()
	val errorInputName: LiveData<Boolean>
		get() = _errorInputName

	private val _taskElement = MutableLiveData<TaskElement>()
	val taskElement: LiveData<TaskElement>
		get() = _taskElement

	private val _shouldCloseScreen = MutableLiveData<Unit>()
	val shouldCloseScreen: LiveData<Unit>
		get() = _shouldCloseScreen

	fun getTaskElement(taskElementId: Int) {
		viewModelScope.launch {
			val element = getTaskElementUseCase.getTaskElement(taskElementId)
			_taskElement.value = element
		}
	}

	fun addTaskElement(inputName: String?, inputDescription: String?) {
		val name = parseInput(inputName)
		val description = parseInput(inputDescription)
		val fieldValid = validateInput(name)
		if (fieldValid) {
			viewModelScope.launch {
				val taskElement = TaskElement(name, description, false)
				addTaskElementUseCase.addTaskElement(taskElement)
				finishWork()
			}
		}
	}

	fun editTaskElement(inputName: String?, inputDescription: String?) {
		val name = parseInput(inputName)
		val description = parseInput(inputDescription)
		val fieldValid = validateInput(name)
		if (fieldValid) {
			_taskElement.value?.let {
				viewModelScope.launch {
					val element = it.copy(name = name, description = description)
					editTaskElementUseCase.editTaskElement(element)
					finishWork()
				}
			}
		}
	}

	private fun parseInput(inputString: String?): String {
		return inputString?.trim() ?: ""
	}

	private fun validateInput(name: String): Boolean {
		var result = true
		if (name.isBlank()) {
			_errorInputName.value = true
			result = false
		}
		return result
	}

	fun resetErrorInputName() {
		_errorInputName.value = false
	}

	private fun finishWork() {
		_shouldCloseScreen.value = Unit
	}
}