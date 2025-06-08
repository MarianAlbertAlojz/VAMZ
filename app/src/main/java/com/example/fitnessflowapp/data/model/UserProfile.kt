package com.example.fitnessflowapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entita reprezentujuca pouzivatelsky profil, ktory sa uklada do lokalnej Room databazy
 *
 * Tato tabulka uchovava zakladne informacie o pouzivatelovi ako pohlavie, vek, vyska, hmotnost,
 * ciel, uroven aktivity a kontaktne udaje.
 *
 * @Entity anotacia oznacuje, ze ide o tabulku v databaze s nazvom "user_profile"
 * @PrimaryKey urcuje jedinecny identifikator zaznamu, ktory sa automaticky generuje
 */
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val gender: Gender?,
    val age: Int?,
    val weight: Int?,
    val height: Int?,
    val goal: Goal?,
    val activityLevel: ActivityLevel?,
    val fullName: String?,
    val nickname: String?,
    val email: String?,
    val phone: String?,
    val avatarUri: String?
)
