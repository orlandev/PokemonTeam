package com.orlandev.pokemonteam.ui.screens.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orlandev.pokemonteam.ui.screens.home.HomeRoute
import com.orlandev.pokemonteam.navigation.AppNavigation
import com.orlandev.pokemonteam.navigation.AppNavigationDestination

object HomeDestination : AppNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeRegionsListGraph(
    isExtendedScreen: Boolean,
    navigateTo: (AppNavigation) -> Unit
) {
    composable(route = HomeDestination.route) {
        HomeRoute(navigateTo = navigateTo, isExtendedScreen)
    }
}
