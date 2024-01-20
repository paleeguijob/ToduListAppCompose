package realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import realaof.realhon.realha.todulistapp.base.model.BaseCommonError
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button.StatusState
import realaof.realhon.realha.todulistapp.ui.theme.DustyGray

class ToduLandingUiState(
    val loading: Boolean? = false,
    val error: BaseCommonError? = null,
    val success: ToduLandingUi? = null
) {

    var landingUiState by mutableStateOf(this)
        private set

    fun updateState(state: ToduLandingUiState) {
        landingUiState = state
    }

    companion object {
        val Saver: Saver<ToduLandingUiState, *> = listSaver(
            save = { listOf(it.loading, it.error, it.success) },
            restore = {
                ToduLandingUiState(
                    loading = it[0] as Boolean,
                    error = it[1] as BaseCommonError,
                    success = it[2] as ToduLandingUi
                )
            }
        )
    }

    //Data model
    data class ToduLandingUi(
        val toduListUi: List<ToduListUi> = emptyList(),
        val filtering: List<StatusState> = emptyList()
    ) {
        data class ToduListUi(
            val user: String = "",
            val todus: List<ToduUi> = emptyList()
        ) {
            data class ToduUi(
                val id: Int = 0,
                val userId: Int = 0,
                val user: String = "",
                val title: String = "",
                val status: ToduStatus = ToduStatus()
            ) {
                data class ToduStatus(
                    val status: FilterType = FilterType.ALL,
                    val color: Color = DustyGray
                )
            }
        }
    }
}