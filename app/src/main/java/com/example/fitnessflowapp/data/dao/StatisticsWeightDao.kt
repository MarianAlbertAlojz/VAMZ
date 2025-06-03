package com.example.fitnessflowapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessflowapp.data.model.StatisticsWeight
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticsWeightDao {
    @Query("SELECT * FROM statistics_weight")
    fun getAllWeights(): Flow<List<StatisticsWeight>>

    @Query("SELECT * FROM statistics_weight WHERE statWeightId = :id")
    suspend fun getWeightById(id: Int): StatisticsWeight?

    @Query("SELECT * FROM statistics_weight WHERE statsId = :statsId")
    fun getWeightsForStats(statsId: Int): Flow<List<StatisticsWeight>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(rec: StatisticsWeight): Long

    @Delete
    suspend fun deleteWeight(rec: StatisticsWeight)
}