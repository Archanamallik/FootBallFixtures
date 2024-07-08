package com.example.footballfixtures.dataclasses

data class LeaguesItem(

    val country_id: Int,
    val image_path: String,
    val last_played_at: String,
    val name: String,
    val short_code: String,
    val sport_id: Int,
    val sub_type: String,
    val type: String,
    val id:Int
)

/*
    "id": 271,
    "sport_id": 1,
    "country_id": 320,
    "name": "Superliga",
    "active": true,
    "short_code": "DNK SL",
    "image_path": "https://cdn.sportmonks.com/images/soccer/leagues/271.png",
    "type": "league",
    "sub_type": "domestic",
    "last_played_at": "2024-05-31 17:00:00",
    "category": 2,
    "has_jerseys": false
},*/