import android.content.Context
import com.example.fitnessflowapp.data.dao.UserProfileDao
import com.example.fitnessflowapp.data.model.UserProfile


/**
 * repository trieda pre pristup k datam o uzivatelskom profile
 * sluzi ako vrstva medzi ViewModelom a room databazou
 *
 * obsahuje metody na ulozenie a nacitanie profilu z databazy
 * pristupuje k dao cez singleton implementaciu
 *
 * @param dao DAO objekt pre pristup k tabulke UserProfile
 */
class UserProfileRepository private constructor(
    private val dao: UserProfileDao
) {
    suspend fun saveProfile(profile: UserProfile) = dao.insertProfile(profile)
    suspend fun getProfile(): UserProfile?    = dao.getProfile()

    companion object {
        @Volatile private var INSTANCE: UserProfileRepository? = null
        /**
         * singleton implementacia repozitara – vrati existujucu alebo vytvori novu instanciu
         *
         * @param context  ziskanie pristupu k databaze
         * @return singleton instancia
         */
        fun getInstance(context: Context): UserProfileRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserProfileRepository(
                    AppDatabase.getInstance(context).userProfileDao()
                ).also { INSTANCE = it }
            }
    }
}
