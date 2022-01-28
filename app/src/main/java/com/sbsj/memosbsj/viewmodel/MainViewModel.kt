package com.sbsj.memosbsj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    init {

    }

    val repository: Repository = Repository.getInstance(AppDatabase.getDatabase(application, viewModelScope))
        //Repository(AppDatabase.getDatabase(application, viewModelScope))

    var allWrittenData: LiveData<List<WrittenData>> = repository.allUsers


    fun insert(writtenData: WrittenData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(writtenData)
    }

    fun delete(order: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(order)
    }

    fun deleteAll(writtenData: WrittenData) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll(writtenData)
    }

//    fun getData(order: Int):LiveData<WrittenData> = viewModelScope.launch(Dispatchers.IO) {
//        Repository.read(writtenData)
//    }

    fun getAll(): LiveData<List<WrittenData>>{
        return allWrittenData
    }

    fun readData(order: Int) = viewModelScope.launch(Dispatchers.IO) {
        _getReadData.postValue(repository.read(order))
    }

    private val _getReadData : MutableLiveData<WrittenData> = MutableLiveData()
    val getReadData : LiveData<WrittenData> get() = _getReadData


}