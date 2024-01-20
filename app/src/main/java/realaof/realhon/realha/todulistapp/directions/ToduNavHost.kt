package realaof.realhon.realha.todulistapp.directions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import realaof.realhon.realha.todulistapp.ui.screen.todus.ToduLandingScreen

@Composable
fun ToduNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ToduLanding.route,
        modifier = modifier
    ) {

//        Uncomment for show splash Screen
//        composable(route = Splash.route) {
//            SplashScreen(
//                onTimeout = {
//                    navController.navigateSingleTop(ToduLanding.route)
//                }
//            )
//        }

        composable(route = ToduLanding.route) {
            ToduLandingScreen()
        }
    }
}

fun NavHostController.navigateSingleTop(route: String) {
    this.navigate(route)
}