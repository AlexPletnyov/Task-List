package com.alexpletnyov.task_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alexpletnyov.task_list.domain.TaskElement
import com.alexpletnyov.task_list.domain.TaskListRepository
import javax.inject.Inject

class TaskListRepositoryImpl @Inject constructor(
	private val taskListDao: TaskListDao,
	private val mapper: TaskListMapper
) : TaskListRepository {

	override suspend fun addTaskElement(taskElement: TaskElement) {
		taskListDao.addTaskList(mapper.mapEntityToDbModel(taskElement))
	}

	override suspend fun deleteTaskElement(taskElement: TaskElement) {
		taskListDao.deleteTaskList(taskElement.id)
	}

	override suspend fun editTaskElement(taskElement: TaskElement) {
		taskListDao.addTaskList(mapper.mapEntityToDbModel(taskElement))
	}

	override suspend fun getTaskElement(taskElementId: Int): TaskElement {
		val dbModel = taskListDao.getTaskElement(taskElementId)
		return mapper.mapDbModelToEntity(dbModel)
	}

	override fun getTaskList(): LiveData<List<TaskElement>> =
		Transformations.map(taskListDao.getTaskList()) {
			mapper.mapListDbModelToListEntity(it)
		}
}