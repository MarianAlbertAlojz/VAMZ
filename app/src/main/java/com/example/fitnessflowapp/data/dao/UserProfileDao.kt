package com.example.fitnessflowapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fitnessflowapp.data.model.UserProfile

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 0")
    suspend fun getProfile(): UserProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)
}
