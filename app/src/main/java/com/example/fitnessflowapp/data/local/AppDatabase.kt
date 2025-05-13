package com.example.fitnessflowapp.data.local

import com.example.fitnessflowapp.data.dao.UserProfileDao
import com.example.fitnessflowapp.data.model.UserProfile

@Database(entities = [UserProfile::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}
