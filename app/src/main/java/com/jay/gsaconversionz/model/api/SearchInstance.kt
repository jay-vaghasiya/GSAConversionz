package com.jay.gsaconversionz.model.api

import com.jay.gsaconversionz.remote.NetworkModule
import okhttp3.OkHttpClient

object SearchInstance {

    val api: SearchInstance by lazy {
        NetworkModule.provideRetrofit(OkHttpClient())
            .create(SearchInstance::class.java)
    }
}