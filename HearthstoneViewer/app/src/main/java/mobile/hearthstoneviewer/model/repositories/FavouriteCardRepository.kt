package mobile.hearthstoneviewer.model.repositories

import mobile.hearthstoneviewer.model.dao.CardDao
import mobile.hearthstoneviewer.model.dao.FavouriteCardDao
import mobile.hearthstoneviewer.model.entities.FavouriteCard

class FavouriteCardRepository (private val favouriteCardDao: FavouriteCardDao) {
    fun getAllFavourites(): List<FavouriteCard> = favouriteCardDao.getAll()
    suspend fun add(favouriteCard: FavouriteCard): Long = favouriteCardDao.insert(favouriteCard)
    suspend fun delete(favouriteCard: FavouriteCard) = favouriteCardDao.delete(favouriteCard)
}