package com.aposalo.todolist.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aposalo.todolist.database.AppDatabase

class AddTaskViewModelFactory(private val mDb: AppDatabase, private val mTaskId: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(mDb, mTaskId) as T
    }

}