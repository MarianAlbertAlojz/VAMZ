import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fitnessflowapp.data.dao.UserProfileDao
import com.example.fitnessflowapp.data.local.Converters
import com.example.fitnessflowapp.data.model.UserProfile

@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

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
