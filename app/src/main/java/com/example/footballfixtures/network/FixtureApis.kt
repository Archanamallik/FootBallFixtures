package com.example.footballfixtures.network



import com.example.footballfixtures.dataclasses.Fixturesdata
import com.example.footballfixtures.utils.API_TOKEN
import com.example.footballfixtures.utils.FIXTURE_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface FixtureApis {

    @GET(FIXTURE_URL)
    suspend fun getFixture(

        @Query("page") page: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Fixturesdata
}