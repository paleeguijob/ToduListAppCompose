package realaof.realhon.realha.todulistapp.base.compose.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: EditableSearchInputState = rememberEditableSearchInputState(hint = "", keyword = ""),
    onSearchAction: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onClickedClearText: () -> Unit = {}
) {
    OutlinedTextField(
        value = state.text,
        placeholder = {
            Text(text = state.hintText)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Coin Icon Searchbar"
            )
        },
        trailingIcon = {
            if (state.text.isNotEmpty()) {
                Image(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Coin Search Remove Icon ",
                    modifier = Modifier
                        .size(dimen.dimen_16)
                        .clickable {
                            state.clearText()
                            onClickedClearText()
                        }
                )
            }
        },
        shape = RoundedCornerShape(dimen.dimen_8),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchAction.invoke(state.text)
            }
        ),
        onValueChange = { newText ->
            state.updateText(newText)
            onValueChange(newText)
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(dimen.dimen_48),
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        state = rememberEditableSearchInputState(hint = "Search", keyword = "Coin")
    )
}