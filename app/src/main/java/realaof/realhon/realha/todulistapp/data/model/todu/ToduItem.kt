package realaof.realhon.realha.todulistapp.data.model.todu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toduListTable")
data class ToduItem(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)