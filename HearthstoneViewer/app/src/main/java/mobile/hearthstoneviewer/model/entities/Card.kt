package mobile.hearthstoneviewer.model.entities

import com.google.gson.annotations.Expose
import mobile.hearthstoneviewer.ui.hscards.CardsViewModel

data class Card(
    val id: Int,
    val classId: Int,
    val rarityId: Int,
    val heath: Int,
    val attack: Int,
    val manaCost: Int,
    var name: String,
    var text: String,
    var image: String,
    var cropImage: String,
    @Expose(deserialize = false)
    var favorite: Boolean)

