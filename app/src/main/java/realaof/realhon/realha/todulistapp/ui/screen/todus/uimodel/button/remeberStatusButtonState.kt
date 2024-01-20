package realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberStatusButtonState(
    buttonStatusState: StatusState
) = rememberSaveable(
    buttonStatusState.selected,
    buttonStatusState.status,
    saver = StatusState.Saver
) {
    StatusState(
        status = buttonStatusState.status,
        selected = buttonStatusState.isSelected
    )
}