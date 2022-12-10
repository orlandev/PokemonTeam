package com.orlandev.pokemonteam.di

import com.orlandev.pokemonteam.data.repositories.RegionRepositoryImpl
import com.orlandev.pokemonteam.domain.repositories.IRegionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providePokemonApi(): PokeApiClient {
        return PokeApiClient()
    }

    @Provides
    fun provideRegionRepository(pokeApiClient: PokeApiClient): IRegionRepository {
        return RegionRepositoryImpl(pokeApiClient)
    }
}