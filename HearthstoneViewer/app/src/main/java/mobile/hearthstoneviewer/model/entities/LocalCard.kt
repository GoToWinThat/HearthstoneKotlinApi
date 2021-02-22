package mobile.hearthstoneviewer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class LocalCard (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val classId: Int,
    val cardTypeId: Int,
    val rarityId: Int,
    val health: Int,
    val attack: Int,
    val manaCost: Int,
    var name: String,
    var text: String,
    var image: String
)