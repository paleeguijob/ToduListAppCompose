package realaof.realhon.realha.todulistapp.ui.screen.todus.compose.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.base.model.enum.todu.FilterType
import realaof.realhon.realha.todulistapp.ui.screen.todus.uimodel.button.StatusState
import realaof.realhon.realha.todulistapp.ui.theme.ToduListAppTheme
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@Composable
fun StatusButton(
    modifier: Modifier = Modifier,
    statusState: StatusState,
    onClickedButton: (StatusState) -> Unit = {}
) {

    Button(
        onClick = {
            statusState.updateSelected(true)
            onClickedButton(statusState)
        },
        border = BorderStroke(width = dimen.dimen_2, color = MaterialTheme.colorScheme.tertiary),
        colors = ButtonDefaults.buttonColors(
            contentColor = if (statusState.isSelected) Color.White else MaterialTheme.colorScheme.tertiary,
            containerColor = if (statusState.isSelected) MaterialTheme.colorScheme.tertiary else Color.White
        ),
        modifier = modifier
    ) {
        Text(
            text = statusState.status.name,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
private fun StatusButtonPreview() {
    ToduListAppTheme {
        StatusButton(
            statusState = StatusState(
                status = FilterType.ALL,
                selected = true
            )
        )
    }
}