package com.jay.gsaconversionz.model.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jay.gsaconversionz.model.api.SearchAPI
import com.jay.gsaconversionz.model.data.Search
import com.jay.gsaconversionz.view.pagination.PagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class SearchRepository @Inject constructor(val searchAPI: SearchAPI                     ) {

    fun getMoviesList(searchQuery: String): LiveData<PagingData<Search>> {
        return Pager(
            config = PagingConfig(pageSize = 4, prefetchDistance = 5, maxSize = 14),
            pagingSourceFactory = { PagingSource(searchAPI, searchQuery) }
        ).liveData
    }


}