package com.alexpletnyov.task_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexpletnyov.task_list.domain.TaskElement
import com.alexpletnyov.task_list.domain.TaskListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object TaskListRepositoryImpl: TaskListRepository {

//	private val taskList = mutableListOf<TaskElement>()
	private val taskList = sortedSetOf<TaskElement>({ p0, p1 -> p0.id.compareTo(p1.id) })
	private val taskListLD = MutableLiveData<List<TaskElement>>()

	private var autoIncrementId = 0

	init {
		for (i in 0..10) {
			val element = TaskElement(
				"Task name $i",
				"Some description...",
				Random.nextBoolean()
			)
			addTaskElement(element)
		}
	}

	override fun addTaskElement(taskElement: TaskElement) {
		if (taskElement.id == TaskElement.UNDEFINED_ID) {
			taskElement.id = autoIncrementId++
		}
		taskList.add(taskElement)
		updateTaskList()
	}

	override fun deleteTaskElement(taskElement: TaskElement) {
		taskList.remove(taskElement)
		updateTaskList()
	}

	override fun editTaskElement(taskElement: TaskElement) {
		val oldElement = getTaskElement(taskElement.id)
		taskList.remove(oldElement)
		addTaskElement(taskElement)
	}

	override fun getTaskElement(taskElementId: Int): TaskElement {
		return taskList.find {
			it.id == taskElementId
		} ?: throw RuntimeException("Element with id $taskElementId not found")
	}

	override fun getTaskList(): LiveData<List<TaskElement>> {
		return taskListLD
	}
	
	private fun updateTaskList() {
		taskListLD.value = taskList.toList()
	}
}