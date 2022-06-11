package com.alexpletnyov.task_list.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.alexpletnyov.task_list.R
import com.alexpletnyov.task_list.di.ApplicationComponent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TaskElementFragment.OnEditingFinishedListener {

	private lateinit var viewModel: MainViewModel
	private lateinit var taskListAdapter: TaskListAdapter
	private var taskElementContainer: FragmentContainerView? = null

	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val component by lazy {
		(application as TaskListApplication).component
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		component.inject(this)

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		taskElementContainer = findViewById(R.id.task_element_container)
		setupRecyclerView()
		viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
		viewModel.taskList.observe(this) {
			taskListAdapter.submitList(it)
		}
		val buttonAddTask = findViewById<FloatingActionButton>(R.id.button_add_task)
		buttonAddTask.setOnClickListener {
			if (isOnePaneMode()) {
				val intent = TaskElementActivity.newIntentAddElement(this)
				startActivity(intent)
			} else {
				launchFragment(TaskElementFragment.newInstanceAddElement())
			}
		}
	}

	override fun onEditingFinished() {
		Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
		supportFragmentManager.popBackStack()
	}

	private fun isOnePaneMode(): Boolean {
		return taskElementContainer == null
	}

	private fun launchFragment(fragment: Fragment) {
		supportFragmentManager.popBackStack()
		supportFragmentManager.beginTransaction()
			.replace(R.id.task_element_container, fragment)
			.addToBackStack(null)
			.commit()
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
			if (isOnePaneMode()) {
				val intent = TaskElementActivity.newIntentEditElement(this, it.id)
				startActivity(intent)
			} else {
				launchFragment(TaskElementFragment.newInstanceEditElement(it.id))
			}
		}
	}

	private fun setupCheckBoxClickListener() {
		taskListAdapter.onTaskElementCheckBoxClickListener = {
			viewModel.changeCompletedState(it)
		}
	}
}