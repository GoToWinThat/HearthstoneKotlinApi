package mobile.hearthstoneviewer.ui.hscards

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.repository.CardRepository
import mobile.hearthstoneviewer.api.repository.DeckRepository
import mobile.hearthstoneviewer.model.ApplicationDatabase
import mobile.hearthstoneviewer.model.entities.*
import mobile.hearthstoneviewer.model.repositories.FavouriteCardRepository
import mobile.hearthstoneviewer.model.repositories.HistoryRepository
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CardsViewModel(application: Application) : AndroidViewModel(application)
{

    var listOfCards = MutableLiveData<List<Card>>()


    private val historyRepository =
            HistoryRepository(ApplicationDatabase.getDatabase(application).historyDao())
    private val repository : CardRepository = CardRepository(IApiCaller.getApiCaller())
   // private val repository2 : DeckRepository = DeckRepository(IApiCaller.getApiCaller())
    private val favouriteCardRepository = FavouriteCardRepository(ApplicationDatabase.getDatabase(application).favouriteCardDao())
    fun getCards()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val response = repository.getCards().awaitResponse()
           // val testsoc = repository2.getDeck("AAECAZ8FCIetA/y4A5XNA4/OA8PRA5vYA/zeA73hAwunCJupA/u4A/O7A/7RA4fUA/7bA/neA/TfA5HkA5LkAwA=").awaitResponse()
           // if (testsoc.isSuccessful)
           // {
           //     val data = response.body()!!
           // }
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
    fun getRecentCards() {

        GlobalScope.launch(Dispatchers.IO) {
            var recentCards = CardList()
            var history = historyRepository.getAllHistory()

            history.forEach { history ->
                run {
                    var card =
                            cardsList.value?.find { card -> card.id == history.cardId }
                    card?.let { recentCards.add(it) }
                }
            }

            listOfCards.postValue(recentCards)
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

    suspend fun addCardToHistory(
            card: Card
    ) {

        val history = History(card.id.toInt(), Date())
        historyRepository.add(history)

    }

    fun getCardsByName(name: String)
    {
        var tmpList = CardList()

        GlobalScope.launch(Dispatchers.IO)
        {
            val response = repository.getCardsByName(name).awaitResponse()
val test2=2
            if (response.isSuccessful)
            {
                val data = response.body()!!
                listOfCards.postValue(data.cards)
            }
            else
            {
                Log.d("api-connection", "response failed")
            }
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

        }
    companion object
    {
        lateinit var selectedCard: Card
        var cardsList = MutableLiveData<List<Card>>()
        lateinit var selectedLocalCard: LocalCard}

}