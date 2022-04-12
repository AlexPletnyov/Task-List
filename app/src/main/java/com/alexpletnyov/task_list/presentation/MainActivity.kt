package com.alexpletnyov.task_list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.alexpletnyov.task_list.R

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel
	private lateinit var taskListAdapter: TaskListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setupRecyclerView()
		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.taskList.observe(this) {
			taskListAdapter.submitList(it)
		}
	}

	private fun setupRecyclerView() {
		val rvTaskList = findViewById<RecyclerView>(R.id.rv_task_list)
		with(rvTaskList) {
			taskListAdapter = TaskListAdapter()
			adapter = taskListAdapter
			recycledViewPool.setMaxRecycledViews(
				TaskListAdapter.VIEW_TYPE_COMPLETED,
				TaskListAdapter.MAX_POOL_SIZE
			)
			recycledViewPool.setMaxRecycledViews(
				TaskListAdapter.VIEW_TYPE_NOT_COMPLETED,
				TaskListAdapter.MAX_POOL_SIZE
			)
		}
		setupCheckBoxClickListener()
		setupClickListener()
		setupSwipeListener(rvTaskList)
	}

	private fun setupSwipeListener(rvTaskList: RecyclerView) {
		val callback = object : ItemTouchHelper.SimpleCallback(
			0,
			ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
		) {
			override fun onMove(
				recyclerView: RecyclerView,
				viewHolder: RecyclerView.ViewHolder,
				target: RecyclerView.ViewHolder
			): Boolean {
				return false
			}

			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
				val element = taskListAdapter.currentList[viewHolder.adapterPosition]
				viewModel.deleteTaskElement(element)
			}
		}
		val itemTouchHelper = ItemTouchHelper(callback)
		itemTouchHelper.attachToRecyclerView(rvTaskList)
	}

	private fun setupClickListener() {
		taskListAdapter.onTaskElementClickListener = {
			Log.d("MainActivity", it.toString())
		}
	}

	private fun setupCheckBoxClickListener() {
		taskListAdapter.onTaskElementCheckBoxClickListener = {
			viewModel.changeCompletedState(it)
		}
	}
}