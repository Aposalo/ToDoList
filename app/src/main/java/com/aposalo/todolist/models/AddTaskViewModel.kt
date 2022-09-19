package com.aposalo.todolist.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.aposalo.todolist.database.AppDatabase
import com.aposalo.todolist.database.TaskEntry

class AddTaskViewModel(database: AppDatabase, taskId: Int) : ViewModel() {

    val task: LiveData<TaskEntry?>?

    init {
        task = database.taskDao()!!.loadTaskById(taskId)
    }
}