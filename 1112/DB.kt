package com.example.m09_sqlite

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
class WorkLog {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var name = ""
    var title = ""
    var detail:String = ""
    var img:String = ""
}

@Dao
interface WorkLogDao {
    @Insert
    fun insertWorklog(workLog: WorkLog)

    @Update
    fun updateWorklog(workLog: WorkLog)

    @Delete
    fun deleteWorklog(workLog: WorkLog)

    @Query("select * from worklog")
    fun getAllWorklogs():MutableList<WorkLog>

    @Query("select * from worklog where id=:id")
    fun getWorklog(id:Int):WorkLog
//
}

@Database(entities=arrayOf(WorkLog::class), version=1, exportSchema=true)
abstract class WorkLogDatabase : RoomDatabase() {
    abstract fun worklogsDao():WorkLogDao
}

