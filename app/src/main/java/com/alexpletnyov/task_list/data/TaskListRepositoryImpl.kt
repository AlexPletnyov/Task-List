package com.alexpletnyov.task_list.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alexpletnyov.task_list.domain.TaskElement
import com.alexpletnyov.task_list.domain.TaskListRepository

class TaskListRepositoryImpl(application: Application) : TaskListRepository {

	private val taskListDao = AppDataBase.getInstance(application).taskListDao()
	private val mapper = TaskListMapper()

	override fun addTaskElement(taskElement: TaskElement) {
		taskListDao.addTaskList(mapper.mapEntityToDbModel(taskElement))
	}

	override fun deleteTaskElement(taskElement: TaskElement) {
		taskListDao.deleteTaskList(taskElement.id)
	}

	override fun editTaskElement(taskElement: TaskElement) {
		taskListDao.addTaskList(mapper.mapEntityToDbModel(taskElement))
	}

	override fun getTaskElement(taskElementId: Int): TaskElement {
		val dbModel = taskListDao.getTaskElement(taskElementId)
		return mapper.mapDbModelToEntity(dbModel)
	}

	override fun getTaskList(): LiveData<List<TaskElement>> =
		Transformations.map(taskListDao.getTaskList()) {
			mapper.mapListDbModelToListEntity(it)
		}
}