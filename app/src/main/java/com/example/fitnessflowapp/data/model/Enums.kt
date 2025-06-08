package com.example.fitnessflowapp.data.model


enum class Gender { MALE, FEMALE }

/**
 * ciel pouzivatela pri cviceni
 */
enum class Goal { LOSE_WEIGHT, GAIN_WEIGHT, MUSCLE_MASS_GAIN, SHAPE_BODY, OTHERS }

/**
 * urovne fyzickej aktivity pouzivatela
 */
enum class ActivityLevel { BEGINNER, INTERMEDIATE, ADVANCED }

/**
 * typy workoutov
 */
enum class WorkoutType    { CARDIO, STRENGTH, HIIT, FLEXIBILITY, BALANCE, YOGA }

/**
 * narocnost treningu – zobrazovana pri workoutoch/exercises
 */
enum class Difficulty     { EASY, MEDIUM, HARD }

/**
 * stav workoutu v planovacom nastroji
 */
enum class ScheduleStatus { SCHEDULED, DONE }

/**
 * polia pouzivatelskeho profilu, ktore je mozne upravit
 */
enum class ProfileField { FullName, Nickname, Email, Phone }