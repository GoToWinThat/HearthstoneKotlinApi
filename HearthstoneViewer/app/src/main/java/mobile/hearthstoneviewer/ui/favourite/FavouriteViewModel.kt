package mobile.hearthstoneviewer.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouriteViewModel:ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Zapisane ulubione użytkownika"
    }
    val text: LiveData<String> = _text
}