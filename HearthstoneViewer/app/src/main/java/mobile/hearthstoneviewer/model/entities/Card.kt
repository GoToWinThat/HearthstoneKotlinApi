package mobile.hearthstoneviewer.model.entities

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
    var cropImage: String
    )
