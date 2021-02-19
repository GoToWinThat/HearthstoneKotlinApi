package mobile.hearthstoneviewer.model.repositories

import androidx.lifecycle.LiveData
import mobile.hearthstoneviewer.model.dao.CardDao
import mobile.hearthstoneviewer.model.entities.LocalCard

class CardRepository(private val cardDao: CardDao) {
    val allCards: LiveData<List<LocalCard>> = cardDao.getAll()

    suspend fun add(card: LocalCard): Long = cardDao.insert(card)
    suspend fun delete(card: LocalCard) = cardDao.delete(card)
    suspend fun update(card: LocalCard) = cardDao.update(card)

}