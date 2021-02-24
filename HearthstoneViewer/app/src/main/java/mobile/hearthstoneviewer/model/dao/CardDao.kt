package mobile.hearthstoneviewer.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import mobile.hearthstoneviewer.model.entities.LocalCard

@Dao
interface CardDao
{
    @Query("SELECT * FROM CARD_TABLE")
    fun getAll(): LiveData<List<LocalCard>>
    @Insert
    suspend fun insert(card: LocalCard):Long

    @Delete
    suspend fun delete(card: LocalCard)

    @Update
    suspend fun update(card: LocalCard)

}