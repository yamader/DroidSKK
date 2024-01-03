package net.dyama.droidskk.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.dyama.droidskk.app.settings.SettingsScreen

@Composable
fun App() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Routes.Settings,
  ) {
    composable(Routes.Settings) { SettingsScreen(navController) }
  }
}
