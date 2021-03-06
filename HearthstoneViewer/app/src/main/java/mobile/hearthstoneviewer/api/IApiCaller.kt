package mobile.hearthstoneviewer.api

import mobile.hearthstoneviewer.api.responses.CardListResponse
import mobile.hearthstoneviewer.model.entities.CardList
import mobile.hearthstoneviewer.model.entities.Deck
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.jar.Attributes


interface IApiCaller
{
    //DECKS
    @GET("deck?$localization&$accessToken")
    fun getDeck(@Query("code") code: String): Call<Deck>

    //CARDS
    @GET("cards?$accessToken&$localization&$pageSize")
    fun getCards() : Call<CardListResponse>

    @GET("cards?$accessToken&$localization&$pageSize&$sort")
    fun getCardsByName(@Query("textFilter") textFilter: String): Call<CardListResponse>

    @GET("cards?$accessToken&$localization")
    fun getCardsByParams(@Query("class") klasa: String,
                         @Query("rarity") rarity: String,
                         @Query("type") type: String,
                         @Query("mana") mana: String,
                         @Query("health") health: String,
                         @Query("attack") attack: String,
    ) : Call<CardListResponse>

    companion object
    {
        private const val accessToken: String = "access_token=US8lAFpVKT3HvqXLygYsUXCTtvpT5QFpGI"
        private const val localization: String ="locale=pl_PL"
        private const val pageSize: String = "pageSize=1024"
        private const val sort: String = "sort=name"
        const val baseUrl = "https://us.api.blizzard.com/hearthstone/"


        var instance : IApiCaller? = null

        fun getApiCaller() : IApiCaller
        {
            val singletonApi = instance
            return if (singletonApi != null)
            {
                singletonApi
            }
            else
            {
                val apiCaller = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(IApiCaller::class.java)
                instance = apiCaller
                apiCaller
            }
        }
    }
}