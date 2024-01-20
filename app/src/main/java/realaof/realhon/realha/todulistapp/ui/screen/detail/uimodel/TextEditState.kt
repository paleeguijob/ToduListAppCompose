package realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

data class TextEditState(
    val hint: String,
    val text: String
) {

    var textEdit by mutableStateOf(text)
        private set

    var hintEdit by mutableStateOf(hint)
        private set

    fun updateHint(hint: String) {
        hintEdit = hint
    }

    fun updateText(text: String) {
        textEdit = text
    }

    companion object {
        val Saver: Saver<TextEditState, *> = listSaver(
            save = { listOf(it.hint, it.text) },
            restore = {
                TextEditState(
                    hint = it[0],
                    text = it[1]
                )
            }
        )
    }
}