package com.example.fitnessflowapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessflowapp.data.model.Statistics
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticsDao {
    @Query("SELECT * FROM statistics")
    fun getAllStats(): Flow<List<Statistics>>

    @Query("SELECT * FROM statistics WHERE statsId = :id")
    suspend fun getStatsById(id: Int): Statistics?

    @Query("SELECT * FROM statistics WHERE userId = :userId")
    fun getStatsForUser(userId: Int): Flow<List<Statistics>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: Statistics): Long

    @Delete
    suspend fun deleteStats(stats: Statistics)
}