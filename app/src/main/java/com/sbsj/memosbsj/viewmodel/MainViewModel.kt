package com.sbsj.memosbsj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sbsj.memosbsj.data.AppDatabase
import com.sbsj.memosbsj.data.Repository
import com.sbsj.memosbsj.data.WrittenData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val Repository: Repository =
        Repository(AppDatabase.getDatabase(application, viewModelScope))

    var allWrittenData: LiveData<List<WrittenData>> = Repository.allUsers


    fun insert(writtenData: WrittenData) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insert(writtenData)
    }


    fun deleteAll(writtenData: WrittenData) = viewModelScope.launch(Dispatchers.IO) {
        Repository.delete(writtenData)
    }

    fun getAll(): LiveData<List<WrittenData>>{
        return allWrittenData
    }

}