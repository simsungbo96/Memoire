package com.sbsj.memosbsj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sbsj.memosbsj.data.AppDatabase
import com.sbsj.memosbsj.data.Repository
import com.sbsj.memosbsj.data.WrittenData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    val Repository: Repository =
        Repository(AppDatabase.getDatabase(application, viewModelScope))

    var allWrittenData: LiveData<List<WrittenData>> = Repository.allUsers


    fun insert(writtenData: WrittenData) = viewModelScope.launch(Dispatchers.IO) {
        Repository.insert(writtenData)
    }

    fun delete(order: Int) = viewModelScope.launch(Dispatchers.IO) {
        Repository.delete(order)
    }

    fun deleteAll(writtenData: WrittenData) = viewModelScope.launch(Dispatchers.IO) {
        Repository.deleteAll(writtenData)
    }

    fun readOneData(order: Int)  = viewModelScope.launch(Dispatchers.IO) {
        Repository.read(order)
    }


    fun getAll(): LiveData<List<WrittenData>>{
        return allWrittenData
    }


}