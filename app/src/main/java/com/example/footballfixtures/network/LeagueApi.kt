package com.example.footballfixtures.network



import com.example.footballfixtures.dataclasses.Fixturesdata
import com.example.footballfixtures.dataclasses.Leaguesdata
import com.example.footballfixtures.utils.API_TOKEN
import com.example.footballfixtures.utils.FIXTURE_URL
import com.example.footballfixtures.utils.LEAGUE_URL
import com.example.footballfixtures.utils.LEAGUE_URL_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LeagueApi {

    @GET(LEAGUE_URL)
    suspend fun getLeague(
        @Query("api_token") apiToken: String = API_TOKEN
    ): Response<Leaguesdata>
    @GET(LEAGUE_URL)
    suspend fun getLeagueList(
        @Query("api_token") apiToken: String = API_TOKEN
    ): Leaguesdata

    @GET(LEAGUE_URL_ID)
    suspend fun getLeaguesByIdApi(
        @Path("countryId") countryId: String,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Leaguesdata
}