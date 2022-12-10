package com.orlandev.pokemonteam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.orlandev.pokemonteam.ui.screens.home.navigation.HomeDestination
import com.orlandev.pokemonteam.ui.screens.home.navigation.homeRegionsListGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    onNavigateToDestination: (AppNavigationDestination, String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.route,
    isExtendedScreen: Boolean,
    appState: AppState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        //All navigation graphs here

        homeRegionsListGraph(isExtendedScreen) { navigationTo ->
            navigateTo(navigationTo, onNavigateToDestination)
        }

    }
}

fun navigateTo(
    navigationTo: AppNavigation,
    onNavigateToDestination: (AppNavigationDestination, String) -> Unit
) {
    //TODO MAKE THE NAVIGATION TO INTERNAL SCREENS
}