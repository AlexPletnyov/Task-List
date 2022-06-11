package com.alexpletnyov.task_list.di

import android.app.Application
import com.alexpletnyov.task_list.data.AppDataBase
import com.alexpletnyov.task_list.data.TaskListDao
import com.alexpletnyov.task_list.data.TaskListRepositoryImpl
import com.alexpletnyov.task_list.domain.TaskListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

	@ApplicationScope
	@Binds
	fun bindsTaskListRepository(impl: TaskListRepositoryImpl): TaskListRepository

	companion object {

		@ApplicationScope
		@Provides
		fun provideTaskListDao(
			application: Application
		): TaskListDao {
			return AppDataBase.getInstance(application).taskListDao()
		}
	}
}