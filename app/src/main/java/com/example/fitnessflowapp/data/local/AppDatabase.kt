
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitnessflowapp.data.dao.ExerciseDao
import com.example.fitnessflowapp.data.dao.UserProfileDao
import com.example.fitnessflowapp.data.dao.WorkoutDao
import com.example.fitnessflowapp.data.local.Converters
import com.example.fitnessflowapp.data.model.Exercise
import com.example.fitnessflowapp.data.model.UserProfile
import com.example.fitnessflowapp.data.model.Workout


/**
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7
 * hlavna trieda Room databazy pre aplikaciu FitnessFlow
 * definuje zoznam entit, konvertory a poskytuje DAO rozhrania pre pracu s datami
 *
 *  zabezpecenie jedinej instancie databazy pocas celeho behu aplikacie
 *
 * @Database urcuje entity triedy, verziu databazy
 * @TypeConverters registruje konvertory pre enum hodnoty (napr. Gender, Goal, ActivityLevel)
 */
@Database(entities = [UserProfile::class, Workout::class, Exercise::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        /**
         * vrati existujucu alebo vytvori novu instanciu databazy ako singleton
         *
         * @param context Android context aplikacie
         * @return singleton instancia AppDatabase
         */
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_flow.db"
                ).build().also { INSTANCE = it }
            }
    }
}
