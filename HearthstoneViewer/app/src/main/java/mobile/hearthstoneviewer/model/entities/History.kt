package mobile.hearthstoneviewer.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import mobile.hearthstoneviewer.converters.DateConverter
import java.util.*

@Entity(
        tableName = "history_table",
)
@TypeConverters(DateConverter::class)
data class History (
        @PrimaryKey()
        var cardId:Int,
        var date: Date
)