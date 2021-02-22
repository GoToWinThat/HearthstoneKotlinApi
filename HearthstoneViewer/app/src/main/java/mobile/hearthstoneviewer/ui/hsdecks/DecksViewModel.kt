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
            "AAECAZ8FBJuuA/y4A4TBA8PRAw3cA5yuA8q4A/24A+q5A+u5A+y5A8rBA57NA7/RA8DRA8rRA+DRAwA=",
    "AAECAZ8FBJuuA4TBA8PRA+DRAw3cA5yuA422A8q4A/24A+q5A+u5A+y5A8rBA57NA7/RA8DRA8rRAwA=",
    "AAECAaIHArIC2dEDDrQB7QKXBogHhgmPlwP7ogP1pwOqywPHzgOk0QPf3QPn3QPz3QMA",
        "AAECAZICAuYFr6IDDv4B9wPDlAPKnAP/rQP5tQPlugPvugP5zAObzgO50gPw1AOK4AOM5AMA",
    "AAECAR8GhwTJBJ+lA4WwA4ewA/bWAwyoAv4M+68D/K8DorkD/7oD3MwDm80Dos4DgtADxtEDudIDAA==",
    "AAECAZ8FCIoHh60D/LgDw9EDm9gD/N4DjeEDveEDC6cIm6kD+7gD87sD/tEDh9QD/tsD+d4D5uEDkeQDkuQDAA==",
    "AAECAZ8FCIetA/y4A4/OA5PQA8PRA5vYA/zeA73hAwunCJupA/u4A/O7A5XNA/7RA4fUA/7bA/neA/TfA5HkAwA=",
    "AAECAf0GAv2kA9a5Aw4wzgfCCLW5A7a5A8u5A5XNA5vNA9fOA8HRA8zSA5PeA9DhA8rjAwA=",
    "AAECAaIHBMsD/6UD2dEDqtIDDbQB1AXuBogH4ge5uAPPuQOqywOL0AOK1APV1AP31AOB5AMA",
    "AAECAZ8FCIetA/y4A8PRA5vYA/zeA43hA73hA/3jAwunCJupA/u4A/O7A/7RA4fUA/7bA/neA+bhA5HkA5LkAwA=",
    "AAECAZICAA/9AvcD5gXKnAOvogP/rQP5tQPlugPvugP5zAObzgO50gPw1AOK4AOM5AMA",
    "AAECAQcEvrkD+cIDq9QDwd4DDUuiBP8HlpQDuLkD87sD9sID4swDp84D0tEDtt4D9+MDlOQDAA==",
    "AAECAf0EBO0FxbgDkssDzc4DDasEtATmBJYFpAe8CJ+bA/+dA8G4A+DMA8fOA/fRA4XkAwA=",
    "AAECAR8CyQSkuQMOqAK1A4cE/gz7rwP8rwOiuQP/ugPczAObzQOizgOC0APG0QO50gMA",
    "AAECAf0EBMMBv6QD2dEDleEDDXGeAbsC7AW+pAPdqQP0qwPCuAONuQP63QOQ4QOR4QPo4QMA",
    )

    private val repository : DeckRepository = DeckRepository(IApiCaller.getApiCaller())
    private val favouriteCardRepository = FavouriteCardRepository(ApplicationDatabase.getDatabase(application).favouriteCardDao())
    fun getDecks()
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
    fun getDeck(code:String)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            var decks = mutableListOf<Deck>()

            val response = repository.getDeck(code).awaitResponse()
            if (response.isSuccessful)
            {
                    val data = response.body()!!
                    decks.add(data)
            }
            searchedDeck.postValue(decks)

        }
    }
    companion object
    {
        lateinit var selectedDeck: Deck
        var allDecks = MutableLiveData<List<Deck>>()
        var searchedDeck = MutableLiveData<List<Deck>>()
    }
}