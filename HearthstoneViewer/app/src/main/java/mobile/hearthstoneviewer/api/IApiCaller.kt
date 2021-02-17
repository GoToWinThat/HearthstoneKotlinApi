package mobile.hearthstoneviewer.api

import mobile.hearthstoneviewer.api.responses.CardListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface IApiCaller
{

    //CARDS
    @GET("cards?$accessToken&$localization")
    fun getCards() : Call<CardListResponse>

    companion object
    {
        private const val accessToken: String = "access_token=USUzOmN6B0sn6t5zhdomNrYzq69KfGyUUe"
        private const val localization: String ="locale=pl_PL"
        private const val baseUrl = "https://us.api.blizzard.com/hearthstone/"

        private var instance : IApiCaller? = null

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