package realaof.realhon.realha.todulistapp.base.compose.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@Composable
fun BaseErrorScreen(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String,
    message: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {

        icon?.apply {
            Image(
                imageVector = Icons.Filled.ErrorOutline,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiary),
                contentDescription = "Error Icon $title"
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier
                .padding(top = dimen.dimen_16)
                .padding(horizontal = dimen.dimen_16)
                .clearAndSetSemantics {
                    contentDescription = "Error, Title : $title"
                }
        )

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.secondary
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = dimen.dimen_12, bottom = dimen.dimen_16)
                .padding(horizontal = dimen.dimen_16)
                .clearAndSetSemantics {
                    contentDescription = "Error, Message: $message"
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BaseErrorScreenPreviewDarkMode() {
    BaseErrorScreen(
        title = "CommonTitle",
        message = "ErrorMessage"
    )
}