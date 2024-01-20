package realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType

@Parcelize
data class StatusState(
    val status: FilterType,
    val selected: Boolean,
) : Parcelable {

    var statusType by mutableStateOf(status)
        private set

    var isSelected by mutableStateOf(selected)

    fun updateSelected(selected: Boolean) {
        isSelected = selected
    }

    fun updateStatusType(status: FilterType) {
        statusType = status
    }

    companion object {
        val Saver: Saver<StatusState, *> = listSaver(
            save = { listOf(it.status, it.selected) },
            restore = {
                StatusState(
                    status = it[0] as FilterType,
                    selected = it[1] as Boolean
                )
            }
        )
    }
}