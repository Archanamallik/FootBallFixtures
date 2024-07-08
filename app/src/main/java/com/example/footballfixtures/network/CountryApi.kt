package com.example.footballfixtures.network



import com.example.footballfixtures.dataclasses.Countrydata
import com.example.footballfixtures.dataclasses.Fixturesdata
import com.example.footballfixtures.dataclasses.Leaguesdata
import com.example.footballfixtures.utils.API_TOKEN
import com.example.footballfixtures.utils.COUNTRY_API
import com.example.footballfixtures.utils.FIXTURE_URL
import com.example.footballfixtures.utils.LEAGUE_URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {

    @GET(COUNTRY_API)
    suspend fun getCountry(
        @Query("page") page: Int,
        //@Path("countryId") countryId: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Countrydata
}