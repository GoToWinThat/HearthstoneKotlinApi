package mobile.hearthstoneviewer.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Zapisane ulubione użytkownika"
    }
    val text: LiveData<String> = _text
}