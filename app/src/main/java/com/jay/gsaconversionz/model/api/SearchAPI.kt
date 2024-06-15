package com.jay.gsaconversionz.model.api

import com.jay.gsaconversionz.model.data.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {

    @GET("/")
    suspend fun getUserList(
        @Query("type") type: String,
        @Query("apikey") apiKey: String,
        @Query("page") page: Int,
        @Query("s") searchQuery: String
    ): Response<MoviesListResponse>
}