package com.ironelder.withinno.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {
    @GET(".")
    fun getStringResponse(): Call<String>
}