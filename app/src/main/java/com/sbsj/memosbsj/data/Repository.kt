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

    suspend fun delete(order: Int) {
        dao.delete(order)
    }
    suspend fun deleteAll(writtenData: WrittenData){
        dao.deleteAll()
    }
    suspend fun read(order: Int) :WrittenData  {
        return dao.searchOne(order)
    }
//    suspend fun deleteAll(writtenData: WrittenData) {
//        dao.delete(writtenData)
//    }

}