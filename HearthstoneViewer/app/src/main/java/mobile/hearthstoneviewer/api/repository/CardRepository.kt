package mobile.hearthstoneviewer.api.repository

import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.responses.CardListResponse
import mobile.hearthstoneviewer.model.entities.CardList
import retrofit2.Call

class CardRepository(private val apiCaller: IApiCaller)
{
    fun getCards():Call<CardListResponse> = apiCaller.getCards()
    fun getCardsByName(textFilter: String):Call<CardListResponse> = apiCaller.getCardsByName(textFilter)
}
