package realaof.realhon.realha.todulistapp.ui.screen.todus.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.ui.screen.todus.compose.item.ToduItem
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState
import realaof.realhon.realha.todulistapp.ui.theme.Malachite
import realaof.realhon.realha.todulistapp.ui.theme.ToduListAppTheme
import realaof.realhon.realha.todulistapp.ui.theme.TreePoppy
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@Composable
fun ToduList(
    modifier: Modifier = Modifier,
    toduListUi: List<ToduLandingUiState.ToduLandingUi.ToduListUi>,
    onToduItemClicked: (ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi) -> Unit = {}
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimen.dimen_24),
        contentPadding = PaddingValues(vertical = dimen.dimen_24),
        modifier = modifier
    ) {

        items(items = toduListUi) { toduListUi ->
            ToduListByUserGroup(
                toduListUi = toduListUi,
                onToduItemClicked = onToduItemClicked
            )
        }
    }
}

@Composable
fun ToduListByUserGroup(
    modifier: Modifier = Modifier,
    toduListUi: ToduLandingUiState.ToduLandingUi.ToduListUi,
    onToduItemClicked: (ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi) -> Unit = {}
) {

    Text(
        text = toduListUi.user,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier
            .padding(horizontal = dimen.dimen_16)
            .padding(bottom = dimen.dimen_8)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(dimen.dimen_24),
        modifier = modifier
    ) {
        toduListUi.todus.forEach { toduUi ->
            ToduItem(
                toduUi = toduUi,
                onToduItemClicked = onToduItemClicked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ToduListPreview() {
    ToduListAppTheme {
        ToduList(
            toduListUi = listOf(
                ToduLandingUiState.ToduLandingUi.ToduListUi(
                    user = "User 1",
                    todus = listOf(
                        ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi(
                            id = 0,
                            userId = 0,
                            title = "laboriosam mollitiam quasi adipisci quia ",
                            status = ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi.ToduStatus(
                                status = FilterType.ACTIVE,
                                color = TreePoppy
                            )
                        ),
                        ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi(
                            id = 0,
                            userId = 0,
                            title = "laboriosam mollitia et enim quasi adipisci quia provident illumlaboriosam mollitia et enim quasi adipisci quia ",
                            status = ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi.ToduStatus(
                                status = FilterType.ACTIVE,
                                color = TreePoppy
                            )
                        ),
                        ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi(
                            id = 0,
                            userId = 0,
                            title = "laboriosam mollitia et enim quasi adipisci quia provident illumlaboriosam mollitia et enim quasi adipisci quia ",
                            status = ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi.ToduStatus(
                                status = FilterType.DONE,
                                color = Malachite
                            )
                        )
                    )
                )
            )
        )
    }
}