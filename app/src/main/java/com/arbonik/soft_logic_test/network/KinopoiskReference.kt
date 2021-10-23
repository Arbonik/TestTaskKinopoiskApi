package com.arbonik.soft_logic_test.network

import com.arbonik.soft_logic_test.data.allMovies.MoviesPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val TOKEN = "ac3ffb39c876fd7d50d5b52843b0aef4"

interface KinopoiskReference {
    @GET("/movies/all/page/1/token/{token}")
    fun getPage(@Path("token") token: String = TOKEN): Call<MoviesPage>
}