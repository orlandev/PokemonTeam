package com.orlandev.pokemonteam.domain.repositories

import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList
import me.sargunvohra.lib.pokekotlin.model.Region

interface RegionRepository {

    suspend fun getRegionList(offset: Int, limit: Int): NamedApiResourceList

    suspend fun getRegionById(id: Int): Region

}