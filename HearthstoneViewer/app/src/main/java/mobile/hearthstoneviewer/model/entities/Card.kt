package mobile.hearthstoneviewer.model.entities

import com.google.gson.annotations.Expose

data class Card(
    val id: Int,
    val classId: Int,
    val cardTypeId: Int,
    val rarityId: Int,
    val health: Int,
    val attack: Int,
    val manaCost: Int,
    var name: String,
    var text: String,
    var image: String,
    var cropImage: String,
    @Expose(deserialize = false)
    var favorite: Boolean
)

class CardList : ArrayList<Card>(){}
