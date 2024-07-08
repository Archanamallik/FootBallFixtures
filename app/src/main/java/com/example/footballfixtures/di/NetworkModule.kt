package com.example.footballfixtures.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.footballfixtures.dataclasses.FixtureItems
import com.example.footballfixtures.network.CountryApi
import com.example.footballfixtures.network.FixtureApis
import com.example.footballfixtures.network.LeagueApi
import com.example.footballfixtures.network.PlayerApi
import com.example.footballfixtures.network.TeamsApi
import com.example.footballfixtures.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    @Singleton
    @Provides
    fun providesFixtureService(retrofit: Retrofit): FixtureApis =
        retrofit.create(FixtureApis::class.java)

    @Singleton
    @Provides
    fun providesLeagueService(retrofit: Retrofit): LeagueApi =
        retrofit.create(LeagueApi::class.java)
    @Singleton
    @Provides
    fun providesCountryService(retrofit: Retrofit): CountryApi =
        retrofit.create(CountryApi::class.java)
    @Singleton
    @Provides
    fun providesTeamsService(retrofit: Retrofit): TeamsApi =
        retrofit.create(TeamsApi::class.java)
    @Provides
    fun providesPlayerService(retrofit: Retrofit): PlayerApi =
        retrofit.create(PlayerApi::class.java)
}