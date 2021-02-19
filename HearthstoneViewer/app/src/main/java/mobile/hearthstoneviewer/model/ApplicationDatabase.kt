package mobile.hearthstoneviewer.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mobile.hearthstoneviewer.model.dao.CardDao
import mobile.hearthstoneviewer.model.dao.FavouriteCardDao
import mobile.hearthstoneviewer.model.entities.FavouriteCard
import mobile.hearthstoneviewer.model.entities.LocalCard

@Database(
    entities = [
        LocalCard::class,
        FavouriteCard::class],
    version = 3,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun favouriteCardDao(): FavouriteCardDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }
            }

        }

    }
}

