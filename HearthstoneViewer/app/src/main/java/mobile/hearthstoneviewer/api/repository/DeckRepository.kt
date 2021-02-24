package mobile.hearthstoneviewer.api.repository

import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.model.entities.Deck
import retrofit2.Call

class DeckRepository(private val apiCaller: IApiCaller)
{
    fun getDeck(code:String): Call<Deck> = apiCaller.getDeck(code)
}