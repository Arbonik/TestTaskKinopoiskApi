package com.arbonik.soft_logic_test.network

import com.arbonik.soft_logic_test.data.allMovies.MoviesPage
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

const val TOKEN = "c4973ec405dc3241a7b52139b76b5881"

interface KinopoiskReference {
    @GET("/movies/all/page/{page}/token/$TOKEN")
    suspend fun getPage(@Path("page") page : Int = 1): Response<MoviesPage>
}