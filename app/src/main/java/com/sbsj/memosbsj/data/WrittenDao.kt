package com.sbsj.memosbsj.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WrittenDao {
    // 데이터 베이스 불러오기
    @Query("SELECT * from memo_table ORDER BY `order` ASC")
    fun getAll(): LiveData<List<WrittenData>>

    // 데이터 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(writtenData: WrittenData)

    // 데이터 전체 삭제
    @Query("DELETE FROM memo_table")
    fun deleteAll()

    // 데이터 업데이트
    @Update
    fun update(entity: WrittenData);

    // 데이터 삭제
    @Query("DELETE FROM memo_table where `order` = :deleteOrder")
    fun delete(deleteOrder: Int);

    //해당데이터 읽기
    @Query("SELECT title FROM memo_table where 'order' = :readOrder")
    fun searchOne(readOrder: Int) : String

}