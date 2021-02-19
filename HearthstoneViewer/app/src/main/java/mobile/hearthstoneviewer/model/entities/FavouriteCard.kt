package mobile.hearthstoneviewer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "favourite_card",)

data class FavouriteCard(
        @PrimaryKey var cardId: Int
)