package mobile.hearthstoneviewer.api

import mobile.hearthstoneviewer.api.responses.CardListResponse
import mobile.hearthstoneviewer.model.entities.Deck
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface IApiCaller
{
    //CARDS
    @GET("deck?$localization&$accessToken")
    fun getDeck(@Query("code") code: String): Call<Deck>

    //CARDS
    @GET("cards?$accessToken&$localization&$pageSize")
    fun getCards() : Call<CardListResponse>

    companion object
    {
        private const val accessToken: String = "access_token=USMaSlx40IDBOpTqSCBJN4OseXku5VHTl3"
        private const val localization: String ="locale=pl_PL"
        private const val pageSize: String = "pageSize=1024"
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