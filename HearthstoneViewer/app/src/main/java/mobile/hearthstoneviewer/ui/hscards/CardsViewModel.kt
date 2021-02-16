package mobile.hearthstoneviewer.ui.hscards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Karty w grze"
    }
    val text: LiveData<String> = _text
}