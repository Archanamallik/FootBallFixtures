package com.example.footballfixtures.network

import com.example.footballdetails.dataclass.Playersdata
import com.example.footballdetails.dataclass.Teamdata
import com.example.footballfixtures.utils.API_TOKEN
import com.example.footballfixtures.utils.COACHBYID_URL
import com.example.footballfixtures.utils.PLAYERBYID_URL
import com.example.footballfixtures.utils.REFEREBYID_URL
import com.example.footballfixtures.utils.TEAMBYID_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {

    @GET(PLAYERBYID_URL)
    suspend fun getPlayerById(
        @Path("countryId") countryId: String,
        @Query("page") page: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Playersdata


    @GET(COACHBYID_URL)
    suspend fun getCoachById(
        @Path("countryId") countryId: String,
        @Query("page") page: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Playersdata

    @GET(REFEREBYID_URL)
    suspend fun getRefereeById(
        @Path("countryId") countryId: String,
        @Query("page") page: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): Playersdata
}