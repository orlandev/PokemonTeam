package com.orlandev.pokemonteam.utils.helpers

import android.util.Log
import androidx.paging.LoadState
import androidx.paging.PagingSource.LoadResult
import androidx.paging.compose.LazyPagingItems
import java.io.IOException

/**
 * `EmptyDataException` is a `data class` that extends `Exception` and has a single `message` property.
 * @property {String} message - The message that will be displayed when the exception is thrown.
 */
data class EmptyDataException(
    override val message: String
) : Exception()


fun <T : Any> LazyPagingItems<T>.shouldInit(): Boolean {
    Log.d(
        "PAGING_ITEMS",
        "check should init ${this.loadState.refresh !is LoadState.Loading && this.loadState.refresh !is LoadState.Error && this.itemCount == 0}"
    )
    return this.loadState.refresh is LoadState.NotLoading && this.loadState.prepend is LoadState.NotLoading && this.loadState.refresh !is LoadState.Error && this.itemCount == 0
}

class PagingHelper {
    companion object {
        private const val TAG = "PagingHelper"

        fun <T : Any> getReturn(
            throwable: Throwable?,
            list: List<T>,
            serverErrorMessage: String?,
            nextPage: Int,
            pageSize: Int,
            currentPage: Int
        ): LoadResult<Int, T> {
            return if (throwable == null) {
                val prevKey = if (nextPage == 1) null else nextPage - 1
                val nextKey = if (pageSize == currentPage) null else currentPage + 1
                if (list.isNotEmpty()) {

                    LoadResult.Page(data = list, prevKey = prevKey, nextKey = nextKey)
                }
                //handle catched error by returning server message
                else if (!serverErrorMessage.isNullOrEmpty())
                    LoadResult.Error(EmptyDataException(serverErrorMessage))

                //handle unpredictable error
                else LoadResult.Error(Exception("Something went wrong"))

                //handle catched error by returning custom error message
            } else run {
                Log.e(TAG, "check error ${throwable.message}")
                LoadResult.Error(IOException(throwable.message.toString()))
            }
        }
    }
}