package com.alexpletnyov.task_list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.alexpletnyov.task_list.domain.TaskElement

class TaskElementDiffCallback : DiffUtil.ItemCallback<TaskElement>() {
	override fun areItemsTheSame(oldItem: TaskElement, newItem: TaskElement): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: TaskElement, newItem: TaskElement): Boolean {
		return oldItem == newItem
	}
}