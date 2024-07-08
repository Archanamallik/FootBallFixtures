package com.example.footballfixtures.network

import com.example.footballdetails.dataclass.Teamdata
import com.example.footballfixtures.utils.API_TOKEN
import com.example.footballfixtures.utils.TEAMBYID_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamsApi {

    @GET(TEAMBYID_URL)
    suspend fun getTeams(

        @Path("countryId") countryId: String,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Response<Teamdata>



}