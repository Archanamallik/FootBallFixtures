package com.example.footballdetails.dataclass

data class TeamsItems (
    val id: Int,

    val country_id: Int,
    val gender: String,
    val name: String,
    val short_code: String,
    val image_path: String,
    val type: String,

val last_played_at:String


    )

/*
{
            "id": 85,
            "sport_id": 1,
            "country_id": 320,
            "venue_id": 5655,
            "gender": "male",
            "name": "FC Copenhagen",
            "short_code": "COP",
            "image_path": "https://cdn.sportmonks.com/images/soccer/teams/21/85.png",
            "founded": 1992,
            "type": "domestic",
            "placeholder": false,
            "last_played_at": "2024-05-31 17:00:00"
        },
   */