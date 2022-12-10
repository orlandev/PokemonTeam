package com.orlandev.pokemonteam.ui.screens.home.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orlandev.pokemonteam.domain.repositories.IRegionRepository
import com.orlandev.pokemonteam.utils.helpers.PagingHelper
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

const val MAX_FETCH = 10

class RegionListPagingSource(
    private val repository: IRegionRepository
) : PagingSource<Int, NamedApiResource>() {

    companion object {
        const val TAG = "RegionListPagingSource"
    }

    override fun getRefreshKey(state: PagingState<Int, NamedApiResource>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NamedApiResource> {
        Log.d(
            TAG,
            "Load function called using params [ KEY: ${params.key} - LOADSIZE: ${params.loadSize}]"
        )
        val nextPage = (params.key ?: 1)
        val result = repository.getRegionList(offset = (params.key ?: 0) * MAX_FETCH, MAX_FETCH)
        val currentPage = params.key ?: 1
        val pageSize = MAX_FETCH
        val list = result.results
        return PagingHelper.getReturn(
            list = list,
            nextPage = nextPage,
            pageSize = pageSize,
            currentPage = currentPage,
            serverErrorMessage = null,
            throwable = null
        )
    }
}