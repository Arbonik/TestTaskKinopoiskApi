package com.arbonik.soft_logic_test.network

import com.arbonik.soft_logic_test.data.allMovies.MoviesPage
import com.arbonik.soft_logic_test.data.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//const val TOKEN = "c4973ec405dc3241a7b52139b76b5881" // 24.10 ~ 12:00
//const val TOKEN = "ebfab0f43b9c9859c00e11a7d40d5962" // 24.10 16:47
//const val TOKEN = "b819ee93ba4e622c897b56e009c7bf3c"
//const val TOKEN = "5ead8e9e555e01d132f89c0745561c5a" // 25.10 1:12
//const val TOKEN = "049caf7d58c9a953e9c34fc7cefbddb6" // 25.10 2:30
//const val TOKEN = "d720c0c8702de29fa7bc5fc880b2386a" //25.10 3:40
//const val TOKEN = "f87c91e6a0ecd8ab6fbcc1c30947e285" // 25.10 3:50
//const val TOKEN = "ac3ffb39c876fd7d50d5b52843b0aef4" // 25.10 3:53
const val TOKEN = "3438acad5b4315aae9d007a48a2fdc75"


interface MoviesReference {
    @GET("/movies/all/page/{page}/token/$TOKEN")
    suspend fun getPageMovies(@Path("page") page: Int): Response<MoviesPage>

    @GET("/movies/{id}/token/$TOKEN")
    suspend fun getMovie(@Path("id") id: String): Response<MovieResponse>
}