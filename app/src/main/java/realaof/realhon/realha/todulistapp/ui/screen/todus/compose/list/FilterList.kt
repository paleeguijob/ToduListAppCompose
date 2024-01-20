package realaof.realhon.realha.todulistapp.ui.screen.todus.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.ui.screen.todus.compose.item.StatusButton
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button.StatusState
import realaof.realhon.realha.todulistapp.ui.theme.ToduListAppTheme
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@Composable
fun FilterList(
    modifier: Modifier = Modifier,
    filtering: List<StatusState>,
    onClickedButton: (StatusState) -> Unit = {}
) {
    var statusFilter by rememberSaveable { mutableStateOf(filtering) }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimen.dimen_8)
    ) {

        items(items = statusFilter) { status ->
            StatusButton(
                statusState = status,
                onClickedButton = { selected ->
                    statusFilter = statusFilter.map {
                        if (it.statusType == selected.statusType) {
                            it.copy(selected = true).apply {
                                this.updateSelected(true)
                            }
                        } else {
                            it.copy(selected = false).apply {
                                this.updateSelected(false)
                            }
                        }
                    }

                    onClickedButton(selected)
                }
            )
        }
    }
}

@Preview
@Composable
private fun FilterListPreview() {
    ToduListAppTheme {
        FilterList(
            filtering = listOf(
                StatusState(
                    status = FilterType.ALL,
                    selected = true
                ),
                StatusState(
                    status = FilterType.ACTIVE,
                    selected = false
                ), StatusState(
                    status = FilterType.DONE,
                    selected = false
                )
            )
        )
    }
}