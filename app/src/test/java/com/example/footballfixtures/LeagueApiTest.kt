package com.example.footballfixtures

import com.example.footballfixtures.network.LeagueApi
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeagueApiTest {
    lateinit var mockWebServer: MockWebServer

    lateinit var leagueApi: LeagueApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        leagueApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LeagueApi::class.java)
    }
@Test
fun testGetLeagues()= runTest {
    val mockResponse = MockResponse()
    mockResponse.setBody("{}")
    mockWebServer.enqueue(mockResponse)

    val leaguesdata = leagueApi.getLeagueList()
    mockWebServer.takeRequest()
Assert.assertEquals(true, leaguesdata.data?.isEmpty()  )
}

@Test
    fun testGetLeagues_return()= runTest {
        val mockResponse = MockResponse()
       val content=Helper.readFileResource("/leagues.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val leaguesdata = leagueApi.getLeagueList()
        mockWebServer.takeRequest()
        Assert.assertEquals(false, leaguesdata.data?.isEmpty()  )
        Assert.assertEquals(2, leaguesdata.data?.size )
    }



    @After
    fun tearDown() {
        mockWebServer.shutdown()

    }
}