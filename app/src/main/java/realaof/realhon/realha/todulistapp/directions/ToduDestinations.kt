package realaof.realhon.realha.todulistapp.directions

interface ToduDestinations {
    val title: String
    val route: String
}

object Splash : ToduDestinations {
    override val title: String = "Todu Splash Screen"
    override val route: String = "todu/splash"
}

object ToduLanding : ToduDestinations {
    override val title: String = "Todu Landing Screen"
    override val route: String = "todu/landing"
}