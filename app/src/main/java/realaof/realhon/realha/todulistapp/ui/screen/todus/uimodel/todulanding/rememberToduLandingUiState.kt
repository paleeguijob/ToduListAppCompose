package realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberToduLandingUiState(
    landingUiState: ToduLandingUiState
) = rememberSaveable(landingUiState, saver = ToduLandingUiState.Saver) {
    ToduLandingUiState(
        loading = landingUiState.loading,
        error = landingUiState.error,
        success = landingUiState.success
    )
}