package com.arbonik.soft_logic_test.network

import com.arbonik.soft_logic_test.data.allMovies.MoviesPage
import com.arbonik.soft_logic_test.data.allMovies.Movy
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//const val TOKEN = "c4973ec405dc3241a7b52139b76b5881"
const val TOKEN = "ebfab0f43b9c9859c00e11a7d40d5962"

interface MoviesReference {
    @GET("/movies/all/page/{page}/token/$TOKEN")
    suspend fun getPageMovies(@Path("page") page: Int): Response<MoviesPage>

    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") id: String): Response<Movy>
}