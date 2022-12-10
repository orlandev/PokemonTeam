package com.orlandev.pokemonteam.data.repositories

import com.orlandev.pokemonteam.domain.repositories.IRegionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList
import me.sargunvohra.lib.pokekotlin.model.Region
import javax.inject.Inject

class RegionRepositoryImpl @Inject constructor(private val pokemonApi: PokeApiClient) :
    IRegionRepository {

    override suspend fun getRegionList(offset: Int, limit: Int): NamedApiResourceList {
        return withContext(Dispatchers.IO) { pokemonApi.getRegionList(offset, limit) }
    }

    override suspend fun getRegionById(id: Int): Region {
        return withContext(Dispatchers.IO) { pokemonApi.getRegion(id) }
    }

}