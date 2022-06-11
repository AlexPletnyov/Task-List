package com.alexpletnyov.task_list.di

import androidx.lifecycle.ViewModel
import com.alexpletnyov.task_list.presentation.MainViewModel
import com.alexpletnyov.task_list.presentation.TaskElementViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel::class)
	fun bindMainViewModel(viewModel: MainViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(TaskElementViewModel::class)
	fun bindTaskElementViewModel(viewModel: TaskElementViewModel): ViewModel
}