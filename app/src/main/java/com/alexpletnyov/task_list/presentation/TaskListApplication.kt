package com.alexpletnyov.task_list.presentation

import android.app.Application
import com.alexpletnyov.task_list.di.DaggerApplicationComponent

class TaskListApplication : Application() {

	val component by lazy {
		DaggerApplicationComponent.factory().create(this)
	}
}