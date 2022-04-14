package com.alexpletnyov.task_list.presentation

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexpletnyov.task_list.R

class TaskElementViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
	val cbName: CheckBox = view.findViewById(R.id.cb_name)
	val tvDescription: TextView = view.findViewById(R.id.tv_description)
}