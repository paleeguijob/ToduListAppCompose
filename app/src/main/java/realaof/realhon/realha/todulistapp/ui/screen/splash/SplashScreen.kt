package realaof.realhon.realha.todulistapp.ui.screen.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import realaof.realhon.realha.todulistapp.R
import realaof.realhon.realha.todulistapp.ui.theme.dimen

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onTimeout: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        scope.launch {
            delay(2000)
            onTimeout()
        }

        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "RealHA Todu List App Logo",
            modifier = Modifier
                .size(dimen.dimen_190)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}