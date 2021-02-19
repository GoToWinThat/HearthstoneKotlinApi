package mobile.hearthstoneviewer.ui.hscards

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.repository.CardRepository
import mobile.hearthstoneviewer.model.ApplicationDatabase
import mobile.hearthstoneviewer.model.entities.*
import mobile.hearthstoneviewer.model.repositories.FavouriteCardRepository
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class CardsViewModel(application: Application) : AndroidViewModel(application)
{

    var listOfCards = MutableLiveData<CardList>()

    private val repository : CardRepository = CardRepository(IApiCaller.getApiCaller())
    private val favouriteCardRepository = FavouriteCardRepository(ApplicationDatabase.getDatabase(application).favouriteCardDao())
    fun getCards()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val response = repository.getCards().awaitResponse()
            if (response.isSuccessful)
            {
                val data = response.body()!!
                cardsList.postValue(data.cards) 
            }
        }
    }
    suspend fun checkIfDrinkIsFavourite(card: Card): Boolean = withContext(Dispatchers.IO) {
        var listOfFavourite = favouriteCardRepository.getAllFavourites()
        return@withContext listOfFavourite.contains(FavouriteCard(card.id.toInt()))
    }
    fun addCardToFavourite(card: Card){
        viewModelScope.launch {
            val favourite = FavouriteCard(card.id.toInt())
            favouriteCardRepository.add(favourite)
        }
    }

    fun deleteCardFromFavourites(card: Card) {
        viewModelScope.launch {
            var fav = FavouriteCard(card.id.toInt())
            favouriteCardRepository.delete(fav)
        }
    }
    fun getFavouriteCards(){
        GlobalScope.launch(Dispatchers.IO) {
            var favouriteCards = CardList()
            var fav = favouriteCardRepository.getAllFavourites()

            fav.forEach { fav ->
                kotlin.run {
                    var card = cardsList.value?.find { card -> card.id == fav.cardId }  //to.string()!!!!!!!!!!@@@@@@@@@@@@@
                    card?.let { favouriteCards.add(it) }
                }
            }
            listOfCards.postValue(favouriteCards)
        }
    }

    //DO TESTÓW
    fun getCardsById(id: Int, doneCallback: ((d: Card) -> Unit)) {

        val apiCaller = Retrofit.Builder()
            .baseUrl(IApiCaller.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IApiCaller::class.java)
        IApiCaller.instance = apiCaller
        apiCaller

//        GlobalScope.launch(Dispatchers.IO) {
//
//            val response = api.getDrinkById(id).awaitResponse()
//
//            if (response.isSuccessful) {
//
//                val data = response.body()
//                if (data != null) {
//                    doneCallback(data)
//                }
//                ///Log.d("myTag", listOfDrinks.value.toString())
//            } else {
//                Log.d("api-connection", "response failed")
//            }
        }



    companion object
    {
        lateinit var selectedCard: Card
        var cardsList = MutableLiveData<List<Card>>()
        lateinit var selectedLocalCard: LocalCard}

}