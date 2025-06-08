package com.example.fitnessflowapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fitnessflowapp.data.model.UserProfile


/**
 * DAO rozhranie pre tabulku user_profile
 * obsahuje metody na nacitanie, vlozenie, aktualizaciu a zmazanie profilu pouzivatela
 *
 * @Dao oznacenie informuje Room, ze toto rozhranie definuje databazove operacie
 */
@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getProfile(): UserProfile?

    @Query("DELETE FROM user_profile WHERE id = 1")
    suspend fun deleteProfile(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)

    //val updatedProfile = currentProfile.copy(age = 25, weight = 80)
    //userProfileDao.updateProfile(updatedProfile)
    //pozor na to aby ostali ostatne hodnoty ulozene nech si neprepisem celu databazu na null
    @Update
    suspend fun updateProfile(profile: UserProfile)
}
