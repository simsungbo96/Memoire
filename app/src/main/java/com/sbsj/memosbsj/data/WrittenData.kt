package com.sbsj.memosbsj.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")
data class WrittenData(
    @PrimaryKey(autoGenerate = true)
    var order: Int,
    var title: String?,
    var date: String?
)
