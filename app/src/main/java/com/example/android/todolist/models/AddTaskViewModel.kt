package com.example.android.todolist.models

import com.example.android.todolist.database.AppDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.example.android.todolist.database.TaskEntry

class AddTaskViewModel(database: AppDatabase, taskId: Int) : ViewModel() {

    val task: LiveData<TaskEntry?>?

    init {
        task = database.taskDao()!!.loadTaskById(taskId)
    }
}