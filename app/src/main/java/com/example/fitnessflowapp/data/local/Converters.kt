package com.example.fitnessflowapp.data.local

import androidx.room.TypeConverter
import com.example.fitnessflowapp.data.model.ActivityLevel
import com.example.fitnessflowapp.data.model.Gender
import com.example.fitnessflowapp.data.model.Goal


/**
 * trieda s konvertormi, ktore prevadzaju enumy na String a opacne
 * Room databaza nevie ukladat enum priamo preto sa uklada ako textovy nazov
 *
 */
class Converters {
    @TypeConverter
    fun fromGender(value: Gender?): String? = value?.name

    @TypeConverter
    fun toGender(value: String?): Gender? = value?.let { Gender.valueOf(it) }

    @TypeConverter
    fun fromGoal(value: Goal?): String? = value?.name

    @TypeConverter
    fun toGoal(value: String?): Goal? = value?.let { Goal.valueOf(it) }

    @TypeConverter
    fun fromActivityLevel(value: ActivityLevel?): String? = value?.name

    @TypeConverter
    fun toActivityLevel(value: String?): ActivityLevel? = value?.let { ActivityLevel.valueOf(it) }
}
