package com.arbonik.soft_logic_test.di

import com.arbonik.soft_logic_test.MoviesPageSource
import com.arbonik.soft_logic_test.network.KinopoiskReference
import com.google.gson.Gson
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
object RetrofitModule {

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
    fun kinopoistReference(retrofit: Retrofit) = retrofit.create(KinopoiskReference::class.java)

    @Provides
    fun source(kinopoiskReference: KinopoiskReference) = MoviesPageSource(kinopoiskReference)
}