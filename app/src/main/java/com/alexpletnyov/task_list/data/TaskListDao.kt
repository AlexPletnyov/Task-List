package com.alexpletnyov.task_list.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskListDao {

	@Query("SELECT * FROM task_element")
	fun getTaskList(): LiveData<List<TaskElementDbModel>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addTaskList(taskElementDbModel: TaskElementDbModel)

	@Query("DELETE FROM task_element WHERE id=:taskElementId")
	suspend fun deleteTaskList(taskElementId: Int)

	@Query("SELECT * FROM task_element WHERE id=:taskElementId LIMIT 1")
	suspend fun getTaskElement(taskElementId: Int): TaskElementDbModel
}