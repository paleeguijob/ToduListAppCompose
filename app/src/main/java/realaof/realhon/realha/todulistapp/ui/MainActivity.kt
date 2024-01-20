package realaof.realhon.realha.todulistapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import realaof.realhon.realha.todulistapp.directions.ToduNavHost
import realaof.realhon.realha.todulistapp.ui.theme.ToduListAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToduListApp()
        }
    }
}

@Composable
fun ToduListApp(modifier: Modifier = Modifier) {
    ToduListAppTheme {
        ToduNavHost(
            navController = rememberNavController(),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToduListAppPreview() {
    ToduListApp()
}