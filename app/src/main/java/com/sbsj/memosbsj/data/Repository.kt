package com.sbsj.memosbsj.data

import androidx.lifecycle.LiveData

class Repository(mDatabase: AppDatabase) {

    private val dao = mDatabase.dao()
    val allUsers: LiveData<List<WrittenData>> = dao.getAll()
    companion object {
        private var sInstance: Repository? = null
        fun getInstance(database: AppDatabase): Repository {
            return sInstance
                ?: synchronized(this) {
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }

    suspend fun insert(writtenData: WrittenData) {
        dao.insert(writtenData)
    }

    suspend fun delete(writtenData: WrittenData) {
        dao.delete(writtenData)
    }

}