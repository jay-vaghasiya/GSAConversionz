package com.jay.gsaconversionz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.jay.gsaconversionz.model.data.Search
import com.jay.gsaconversionz.model.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    fun list(searchQuery: String): LiveData<PagingData<Search>> {
        return searchRepository.getMoviesList(searchQuery)
    }




}
