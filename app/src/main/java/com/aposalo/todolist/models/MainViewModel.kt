package com.aposalo.todolist.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aposalo.todolist.database.AppDatabase.getInstance
import com.aposalo.todolist.database.TaskEntry


class MainViewModel(application: Application) : AndroidViewModel(application) {
    val tasks: LiveData<List<TaskEntry?>?>?

    init {
        val database = getInstance(getApplication())
        Log.d(TAG, "Actively retrieving the tasks from the DataBase")
        tasks = database.taskDao()!!.loadAllTasks()
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
}