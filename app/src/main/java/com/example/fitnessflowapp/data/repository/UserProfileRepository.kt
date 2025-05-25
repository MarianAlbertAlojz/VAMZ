import android.content.Context
import com.example.fitnessflowapp.data.dao.UserProfileDao
import com.example.fitnessflowapp.data.model.UserProfile

class UserProfileRepository private constructor(
    private val dao: UserProfileDao
) {
    suspend fun saveProfile(profile: UserProfile) = dao.insertProfile(profile)
    suspend fun getProfile(): UserProfile?    = dao.getProfile()

    companion object {
        @Volatile private var INSTANCE: UserProfileRepository? = null

        fun getInstance(context: Context): UserProfileRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserProfileRepository(
                    AppDatabase.getInstance(context).userProfileDao()
                ).also { INSTANCE = it }
            }
    }
}
