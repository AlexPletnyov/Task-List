package com.alexpletnyov.task_list.presentation

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexpletnyov.task_list.R

class TaskElementViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
	val cbName = view.findViewById<CheckBox>(R.id.cb_name)
	val tvDescription = view.findViewById<TextView>(R.id.tv_description)
}