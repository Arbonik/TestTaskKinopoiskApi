package com.arbonik.soft_logic_test.di

import com.arbonik.soft_logic_test.network.KinopoiskReference
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

    @Provides
    @Singleton
    fun retrofitClient() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun kinopoistReference(retrofit: Retrofit) = retrofit.create(KinopoiskReference::class.java)

}