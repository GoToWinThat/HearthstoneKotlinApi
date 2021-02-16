package mobile.hearthstoneviewer.ui.hsdecks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DecksViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Talie w grze"
    }
    val text: LiveData<String> = _text
}