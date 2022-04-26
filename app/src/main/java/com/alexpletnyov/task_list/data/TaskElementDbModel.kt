package com.alexpletnyov.task_list.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_element")
data class TaskElementDbModel(
	@PrimaryKey(autoGenerate = true)
	val id: Int,
	val name: String,
	val description: String,
	val completed: Boolean
)