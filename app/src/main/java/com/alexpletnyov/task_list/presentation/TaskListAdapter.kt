package com.alexpletnyov.task_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alexpletnyov.task_list.R
import com.alexpletnyov.task_list.domain.TaskElement

class TaskListAdapter : ListAdapter<TaskElement, TaskElementViewHolder>(TaskElementDiffCallback()) {

	var onTaskElementCheckBoxClickListener: ((TaskElement) -> Unit)? = null
	var onTaskElementClickListener: ((TaskElement) -> Unit)? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskElementViewHolder {
		val layout = when (viewType) {
			VIEW_TYPE_COMPLETED -> R.layout.task_completed
			VIEW_TYPE_NOT_COMPLETED -> R.layout.task_not_completed
			else -> throw RuntimeException("Unknown view type: $viewType")
		}
		val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
		return TaskElementViewHolder(view)
	}

	override fun onBindViewHolder(holder: TaskElementViewHolder, position: Int) {
		val taskElement = getItem(position)
		holder.cbName.text = taskElement.name
		holder.tvDescription.text = taskElement.description
		holder.cbName.isChecked = taskElement.completed
		holder.cbName.setOnClickListener {
			onTaskElementCheckBoxClickListener?.invoke(taskElement)
		}
		holder.view.setOnClickListener {
			onTaskElementClickListener?.invoke(taskElement)
		}
	}

	override fun getItemViewType(position: Int): Int {
		val element = getItem(position)
		return if (element.completed) {
			VIEW_TYPE_COMPLETED
		} else {
			VIEW_TYPE_NOT_COMPLETED
		}
	}

	companion object {
		const val VIEW_TYPE_COMPLETED = 100
		const val VIEW_TYPE_NOT_COMPLETED = 101

		const val MAX_POOL_SIZE = 30
	}
}