package mobile.hearthstoneviewer.model.dao

import androidx.room.*
import mobile.hearthstoneviewer.model.entities.FavouriteCard

@Dao
interface FavouriteCardDao {
    @Query("SELECT * FROM favourite_card")
    fun getAll(): List<FavouriteCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteCard: FavouriteCard):Long

    @Delete
    suspend fun delete(favouriteCard: FavouriteCard)
}
