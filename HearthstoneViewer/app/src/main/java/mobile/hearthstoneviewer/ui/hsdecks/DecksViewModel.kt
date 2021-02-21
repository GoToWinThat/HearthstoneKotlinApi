package mobile.hearthstoneviewer.ui.hsdecks

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.repository.CardRepository
import mobile.hearthstoneviewer.api.repository.DeckRepository
import mobile.hearthstoneviewer.model.ApplicationDatabase
import mobile.hearthstoneviewer.model.entities.Card
import mobile.hearthstoneviewer.model.entities.Deck
import mobile.hearthstoneviewer.model.entities.LocalCard
import mobile.hearthstoneviewer.model.repositories.FavouriteCardRepository
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel
import retrofit2.awaitResponse

class DecksViewModel (application: Application) : AndroidViewModel(application)
{
    var listOfDecks = MutableLiveData<List<Deck>>()
    val codes = listOf<String>("AAECAZ8FCIetA/y4A5XNA4/OA8PRA5vYA/zeA73hAwunCJupA/u4A/O7A/7RA4fUA/7bA/neA/TfA5HkA5LkAwA=",
    "AAECAR8eqAK1A8cDhwTbCf4M/KMDpqUD+a4D+68D/K8Dh7ADorkDpLkD/7oD174D3r4D3MwDm80Dos4DgtADxtEDudID9tYD6OED8uED8+EDhOIDj+MDyuMDAAA=",
    "AAECAaIHAtnRA6rSAw60AcsD7gaIB+IHubgDz7kDqssDiNADi9ADitQD1dQD99QDgeQDAA==",
    "AAECAZ8FBJuuA/y4A4TBA8PRAw3cA5yuA8q4A/24A+q5A+u5A+y5A8rBA57NA7/RA8DRA8rRA+DRAwA=")

    private val repository : DeckRepository = DeckRepository(IApiCaller.getApiCaller())
    private val favouriteCardRepository = FavouriteCardRepository(ApplicationDatabase.getDatabase(application).favouriteCardDao())
    fun getCards()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            var decks = mutableListOf<Deck>()
            for(i in codes)
            {
                val response = repository.getDeck(i).awaitResponse()
                if (response.isSuccessful)
                {
                    val data = response.body()!!
                    decks.add(data)
                }
            }
            listOfDecks.postValue(decks)
            allDecks.postValue(decks)

        }
    }
    companion object
    {
        lateinit var selectedDeck: Deck
        var allDecks = MutableLiveData<List<Deck>>()
    }
}