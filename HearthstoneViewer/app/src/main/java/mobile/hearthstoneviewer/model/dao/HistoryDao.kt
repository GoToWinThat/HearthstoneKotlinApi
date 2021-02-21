package mobile.hearthstoneviewer.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mobile.hearthstoneviewer.model.entities.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history_table order by date desc ")
    fun getAll(): List<History>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: History):Long
}