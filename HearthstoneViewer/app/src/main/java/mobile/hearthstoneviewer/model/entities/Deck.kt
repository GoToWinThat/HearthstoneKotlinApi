package mobile.hearthstoneviewer.model.entities

import com.google.gson.annotations.SerializedName

data class Deck(
        val deckCode: String,
        @SerializedName("class")
        val heroClass: HeroClass,
        val cards: List<Card>,
)
