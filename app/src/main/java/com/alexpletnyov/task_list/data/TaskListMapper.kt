package com.alexpletnyov.task_list.data

import com.alexpletnyov.task_list.domain.TaskElement

class TaskListMapper {

	fun mapEntityToDbModel(taskElement: TaskElement) = TaskElementDbModel(
		id = taskElement.id,
		name = taskElement.name,
		description = taskElement.description,
		completed = taskElement.completed
	)

	fun mapDbModelToEntity(taskElementDbModel: TaskElementDbModel) = TaskElement(
		id = taskElementDbModel.id,
		name = taskElementDbModel.name,
		description = taskElementDbModel.description,
		completed = taskElementDbModel.completed
	)

	fun mapListDbModelToListEntity(list: List<TaskElementDbModel>) = list.map {
		mapDbModelToEntity(it)
	}
}