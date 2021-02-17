package mobile.hearthstoneviewer.ui.hscards

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mobile.hearthstoneviewer.api.IApiCaller
import mobile.hearthstoneviewer.api.repository.CardRepository
import mobile.hearthstoneviewer.model.entities.Card
import retrofit2.awaitResponse

class CardsViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository : CardRepository = CardRepository(IApiCaller.getApiCaller())

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
    companion object
    {
        var cardsList = MutableLiveData<List<Card>>()
    }

}