package com.orlandev.pokemonteam.navigation

sealed interface AppNavigation {
    data class RegionNav(val id: Int) : AppNavigation
    data class TeamNav(val id: Int) : AppNavigation
}