package realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberTextEditInputState(
    hint: String,
    text: String
) =
    rememberSaveable(hint, text, saver = TextEditState.Saver) {
        TextEditState(
            hint = hint,
            text = text
        )
    }