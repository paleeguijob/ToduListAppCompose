package realaof.realhon.realha.todulistapp.ui.screen.todus.compose.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.todulanding.ToduLandingUiState
import realaof.realhon.realha.todulistapp.ui.theme.ToduListAppTheme
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@Composable
fun ToduItem(
    modifier: Modifier = Modifier,
    toduUi: ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi,
    onToduItemClicked: (ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToduItemClicked(toduUi) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimen.dimen_16)
        ) {
            Text(
                text = toduUi.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .clearAndSetSemantics {
                        contentDescription = "Todu Item Title: ${toduUi.title}"
                    }
            )

            Spacer(modifier = Modifier.height(dimen.dimen_8))

            Text(
                text = toduUi.status.status.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = toduUi.status.color
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .clearAndSetSemantics {
                        contentDescription = "Todu Item Status: ${toduUi.status.status}"
                    }
            )
        }
    }
}

@Preview
@Composable
private fun ToduItemPreview() {
    ToduListAppTheme {
        ToduItem(
            toduUi = ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi(
                user = "user 1",
                title = "i quia provident ii quia provident i".repeat(1),
                status = ToduLandingUiState.ToduLandingUi.ToduListUi.ToduUi.ToduStatus(
                    status = FilterType.ACTIVE,
                    color = MaterialTheme.colorScheme.tertiary
                )
            ),
            onToduItemClicked = {}
        )
    }
}