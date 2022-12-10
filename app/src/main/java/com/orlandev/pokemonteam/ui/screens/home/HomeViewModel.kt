package com.orlandev.pokemonteam.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orlandev.pokemonteam.domain.repositories.IRegionRepository
import com.orlandev.pokemonteam.ui.screens.home.paging.RegionListPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val regionRepository: IRegionRepository,
) : ViewModel() {
    var regionList: Flow<PagingData<NamedApiResource>> = Pager(PagingConfig(pageSize = 10)) {
        RegionListPagingSource(
            regionRepository
        )
    }.flow.cachedIn(viewModelScope)

}



