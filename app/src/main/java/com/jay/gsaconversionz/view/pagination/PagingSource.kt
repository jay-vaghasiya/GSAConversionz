package com.jay.gsaconversionz.view.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jay.gsaconversionz.model.api.SearchAPI
import com.jay.gsaconversionz.model.data.Search
import com.jay.gsaconversionz.util.Constants

class PagingSource(
    val searchAPI: SearchAPI,
    val searchQuery: String,
) :
    PagingSource<Int, Search>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val position = params.key ?: 1
            val response = searchAPI.getUserList(
                type = Constants.MOVIE,
                apiKey = Constants.APIKEY,
                page = position,
                searchQuery = searchQuery
            )
//            response.body()?.Search?.forEach {
//                searchRepository.insert(
//                    SearchEntity(
//                        id = 0,
//                        title = it.Title,
//                        year = it.Year,
//                        imdbId = it.imdbID,
//                        type = it.Type,
//                        poster = it.Poster
//                    )
//                )
//            }
            Log.d("pagingData", "Loaded ${response.body()?.Search?.size} items")
            return LoadResult.Page(
                data = response.body()?.Search ?: emptyList(),
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (position == response.body()!!.totalResults.toInt()) null else position + 1
            )
        } catch (e: Exception) {
            Log.d("pagingData", "Error: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}