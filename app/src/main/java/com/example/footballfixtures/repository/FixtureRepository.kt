package com.example.footballfixtures.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.footballdetails.dataclass.PlayersItems
import com.example.footballdetails.dataclass.TeamsItems
import com.example.footballfixtures.dataclasses.CountryItems
import com.example.footballfixtures.dataclasses.Countrydata
import com.example.footballfixtures.dataclasses.FixtureItems
import com.example.footballfixtures.dataclasses.Leaguesdata
import com.example.footballfixtures.network.CountryApi
import com.example.footballfixtures.network.FixtureApis
import com.example.footballfixtures.network.LeagueApi
import com.example.footballfixtures.network.PlayerApi
import com.example.footballfixtures.network.TeamsApi
import com.example.footballfixtures.paging.CountryPagingSource
import com.example.footballfixtures.paging.FixturePagingSource
import com.example.footballfixtures.paging.PlayerPagingSource
import com.example.footballfixtures.utils.NetworkResult
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


import javax.inject.Inject
import javax.inject.Singleton

class FixtureRepository @Inject constructor(private val fixtureApis: FixtureApis,
                                            private val leagueApi: LeagueApi  ,
                                            private val countryApi: CountryApi,
                                            private val playerApi: PlayerApi,
    private  val teamsApi: TeamsApi
){

    private val _teams = MutableStateFlow<List<TeamsItems>>(emptyList())
    val teams: StateFlow<List<TeamsItems>>
        get() = _teams

    fun getFixture() = Pager<Int,FixtureItems>(
        config = PagingConfig(
            pageSize = 25, maxSize = 100
        ),
        pagingSourceFactory = {
            FixturePagingSource(fixtureApis)
        }
    ).flow

    suspend fun getLeaguesList() :Leaguesdata{
        return leagueApi.getLeagueList()

    }
    suspend fun getLeagues() : NetworkResult<Leaguesdata> {
        val response= leagueApi.getLeague()
        return if (response.isSuccessful){
            val responseBody=response.body()
            if(responseBody!=null)
                NetworkResult.Success(responseBody)
            else
                NetworkResult.Error("Something went wrong")
        }else
            NetworkResult.Error("Something went wrong")

    }
    suspend fun getLeaguesById(countryId:String) :Leaguesdata{
        return leagueApi.getLeaguesByIdApi(countryId)

    }

    fun getCountry() = Pager<Int,CountryItems>(
        config = PagingConfig(
            pageSize = 25, maxSize = 100
        ),
        pagingSourceFactory = {
            CountryPagingSource(countryApi)
        }
    ).flow
    suspend fun getTeams(countryId:String) {
        val result = teamsApi.getTeams(countryId)
        if (result.isSuccessful && result.body() != null) {
            _teams.emit(result.body()!!.data)
        }
    }

    fun getPlayers(countryId:String) = Pager<Int,PlayersItems>(
        config = PagingConfig(
            pageSize = 25, maxSize = 100
        ),
        pagingSourceFactory = {
            PlayerPagingSource(playerApi,countryId,0)
        }
    ).flow
    fun getCoaches(countryId:String) = Pager<Int,PlayersItems>(
        config = PagingConfig(
            pageSize = 25, maxSize = 100
        ),
        pagingSourceFactory = {
            PlayerPagingSource(playerApi,countryId,1)
        }
    ).flow
    fun getReferees(countryId:String) = Pager<Int,PlayersItems>(
        config = PagingConfig(
            pageSize = 25, maxSize = 100
        ),
        pagingSourceFactory = {
            PlayerPagingSource(playerApi,countryId,2)
        }
    ).flow

}


