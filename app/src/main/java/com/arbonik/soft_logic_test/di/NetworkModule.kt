package com.arbonik.soft_logic_test.di

import com.arbonik.soft_logic_test.network.MoviesPageSource
import com.arbonik.soft_logic_test.network.MoviesReference
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://api.kinopoisk.cloud"
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun retrofitClient() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun kinopoistReference(retrofit: Retrofit) = retrofit.create(MoviesReference::class.java)

    @Provides
    fun source(moviesReference: MoviesReference) = MoviesPageSource(moviesReference)
}