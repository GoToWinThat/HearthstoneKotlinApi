package mobile.hearthstoneviewer.api.repository

import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.responses.CardListResponse
import retrofit2.Call

class CardRepository(private val apiCaller: IApiCaller)
{
    fun getCards():Call<CardListResponse> = apiCaller.getCards()
}
