package mobile.hearthstoneviewer.model.repositories

import mobile.hearthstoneviewer.model.dao.HistoryDao
import mobile.hearthstoneviewer.model.entities.History

class HistoryRepository(private val historyDao: HistoryDao)
{
    fun getAllHistory(): List<History> = historyDao.getAll()
    suspend fun add(history: History): Long = historyDao.insert(history)

}