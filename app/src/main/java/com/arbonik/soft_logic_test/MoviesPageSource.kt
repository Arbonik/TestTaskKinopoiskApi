package com.arbonik.soft_logic_test

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arbonik.soft_logic_test.data.allMovies.Movy
import com.arbonik.soft_logic_test.network.KinopoiskReference
import retrofit2.HttpException
import javax.inject.Inject

class MoviesPageSource @Inject constructor(
    val kinopoiskReference: KinopoiskReference
) : PagingSource<Int, Movy>() {
    override fun getRefreshKey(state: PagingState<Int, Movy>): Int? {
        val position = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(position) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movy> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val response  = kinopoiskReference.getPage(page)
        Log.d("RESPONCERETROFIT", page.toString())

        if (response.isSuccessful){
            val body = checkNotNull(response.body())
            Log.d("RESPONCERETROFIT", body.toString())
            val nextKey = if (body.movies.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(body.movies, prevKey,nextKey)
        }
        else {
            return LoadResult.Error(HttpException(response))
        }
    }
}