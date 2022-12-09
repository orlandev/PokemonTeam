package com.orlandev.pokemonteam.data.repositories

import com.orlandev.pokemonteam.domain.repositories.RegionRepository
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList
import me.sargunvohra.lib.pokekotlin.model.Region
import javax.inject.Inject

class RegionRepositoryImpl @Inject constructor(private val pokemonApi: PokeApiClient) :
    RegionRepository {

    override suspend fun getRegionList(offset: Int, limit: Int): NamedApiResourceList {
        return pokemonApi.getRegionList(offset, limit)
    }

    override suspend fun getRegionById(id: Int): Region {
        return pokemonApi.getRegion(id)
    }

}