package realaof.realhon.realha.todulistapp.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.R
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.DetailUi
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.TextEditState
import realaof.realhon.realha.todulistapp.ui.screen.detail.uimodel.rememberTextEditInputState
import realaof.realhon.realha.todulistapp.ui.theme.ToduListAppTheme
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToduDetailBottomSheetScreen(
    modifier: Modifier = Modifier,
    detailUi: DetailUi,
    bottomSheetState: SheetState,
    onDismissRequest: (Boolean) -> Unit,
    onClickedSave: (DetailUi) -> Unit = {},
    onDoneAction: () -> Unit
) {
    val state = rememberTextEditInputState(
        hint = stringResource(id = R.string.realha_todu_list_detail_edit_text_hint),
        text = detailUi.title
    )

    ModalBottomSheet(
        windowInsets = WindowInsets.navigationBars,
        sheetState = bottomSheetState,
        onDismissRequest = {
            onDismissRequest(false)
        },
        modifier = modifier,
    ) {
        BottomSheetDetail(
            detailUi = detailUi,
            state = state,
            onClickedSave = onClickedSave,
            onDoneAction = onDoneAction,
            onDismissRequest = onDismissRequest,
            modifier = Modifier.padding(horizontal = dimen.dimen_24)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BottomSheetDetail(
    modifier: Modifier = Modifier,
    detailUi: DetailUi,
    state: TextEditState = rememberTextEditInputState(hint = "", text = ""),
    onClickedSave: (DetailUi) -> Unit = {},
    onDismissRequest: (Boolean) -> Unit = {},
    onDoneAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimen.dimen_16),
        modifier = modifier
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            //Save Button
            Text(
                text = stringResource(id = R.string.realha_todu_list_detail_edit_header_save),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.tertiary
                ),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        onClickedSave(detailUi.copy(title = state.textEdit))
                        keyboardController?.hide()
                        onDismissRequest(false)
                    }
            )

            //Edit Title
            Text(
                text = stringResource(id = R.string.realha_todu_list_detail_edit_header_title),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        //User name Title
        Text(
            text = detailUi.user,
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.Black
            )
        )

        //Text Edit
        OutlinedTextField(
            value = state.textEdit,
            placeholder = {
                Text(text = state.hintEdit)
            },
            shape = RoundedCornerShape(dimen.dimen_8),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.background
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDoneAction()
                    keyboardController?.hide()
                }
            ),
            onValueChange = { newText ->
                state.updateText(newText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(dimen.dimen_190),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetDetailPreview() {
    ToduListAppTheme {
        BottomSheetDetail(
            detailUi = DetailUi(
                id = 0,
                userId = 0,
                user = "User1",
                title = "RealHON RealAOF stay together".repeat(10),
                completed = true
            ),
            modifier = Modifier
                .padding(horizontal = dimen.dimen_24, vertical = dimen.dimen_16)
        )
    }
}