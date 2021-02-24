package mobile.hearthstoneviewer.api.repository

import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.responses.CardListResponse
import retrofit2.Call

class CardRepository(private val apiCaller: IApiCaller)
{
    fun getCards():Call<CardListResponse> = apiCaller.getCards()
    fun getCardsByName(textFilter: String):Call<CardListResponse> = apiCaller.getCardsByName(textFilter)
    fun getCardByParams(klasa: String, rarity: String, type: String, mana: String, health: String, attack: String)
    =apiCaller.getCardsByParams(klasa, rarity, type, mana, health, attack)
}
