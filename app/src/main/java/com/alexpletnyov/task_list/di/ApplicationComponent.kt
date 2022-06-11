package com.alexpletnyov.task_list.di

import android.app.Application
import com.alexpletnyov.task_list.presentation.MainActivity
import com.alexpletnyov.task_list.presentation.TaskElementFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
	modules = [
		DataModule::class,
		ViewModelModule::class
	]
)
interface ApplicationComponent {

	fun inject(activity: MainActivity)

	fun inject(fragment: TaskElementFragment)

	@Component.Factory
	interface Factory {

		fun create(
			@BindsInstance application: Application
		): ApplicationComponent
	}
}