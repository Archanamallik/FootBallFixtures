package com.example.footballfixtures.repository

import androidx.lifecycle.SavedStateHandle
import com.example.footballfixtures.dataclasses.Leaguesdata
import com.example.footballfixtures.network.CountryApi
import com.example.footballfixtures.network.FixtureApis
import com.example.footballfixtures.network.LeagueApi
import com.example.footballfixtures.network.PlayerApi
import com.example.footballfixtures.network.TeamsApi
import com.example.footballfixtures.utils.NetworkResult
import com.example.footballfixtures.viewmodel.FixtureViewModel
import kotlinx.coroutines.test.runTest

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class FixtureRepositoryTest {
@Mock
    lateinit var leagueApi: LeagueApi
    @Mock
    lateinit var fixtureApis: FixtureApis
    @Mock
    lateinit var teamsApi: TeamsApi
    @Mock
    lateinit var playerApi: PlayerApi
    @Mock
    lateinit var countryApi: CountryApi

@Before
fun setUp(){
  MockitoAnnotations.initMocks(this)

}
    @Test
    fun  testGetLeagueList()= runTest{
        Mockito.`when`(leagueApi.getLeague()).thenReturn(
            Response.success(Leaguesdata())

        )
        val sut= FixtureRepository(fixtureApis,leagueApi,countryApi,playerApi,teamsApi)
        val result=sut.getLeagues()
        Assert.assertEquals(true ,result is NetworkResult.Success )
        Assert.assertEquals(0, result.data?.data?.size?:0)
    }

}