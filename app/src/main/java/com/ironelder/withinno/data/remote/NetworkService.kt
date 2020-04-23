package com.ironelder.withinno.data.remote

import retrofit2.Call
import retrofit2.http.GET

//https://www.thewrap.com/marvel-movies-ranked-worst-best-avengers-endgame-x-men-dark-phoenix-spider-man-far-from-home-coronavirus/

interface NetworkService {
    @GET(".")
    fun getStringResponse(): Call<String>
}