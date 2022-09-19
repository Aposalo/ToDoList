package com.example.android.todolist.models

import com.example.android.todolist.database.AppDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddTaskViewModelFactory(private val mDb: AppDatabase, private val mTaskId: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(mDb, mTaskId) as T
    }

}