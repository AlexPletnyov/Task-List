package com.alexpletnyov.task_list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.alexpletnyov.task_list.R

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel

	private var count = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.taskList.observe(this) {
			Log.d("MainActivityTest", it.toString())
			if (count == 0) {
				count++
				val element = it[0]
				viewModel.changeCompletedState(element)
			}
		}
	}
}