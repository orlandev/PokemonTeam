package com.orlandev.pokemonteam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePokemonApi(): PokeApi{
        return PokeApiClient()
    }
}