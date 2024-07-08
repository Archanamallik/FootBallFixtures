package com.example.footballfixtures.dataclasses

data class FixtureItems(

    val id: Int,
    val league_id: Int,
    val name: String,
    val result_info: String,
    val round_id: Int,
    val season_id: Int,
    val sport_id: Int,
    val stage_id: Int,
    val starting_at: String,
    val starting_at_timestamp: Long,
    val state_id: Int,

)
/*
 {
            "id": 216268,
            "sport_id": 1,
            "league_id": 271,
            "season_id": 1273,
            "stage_id": 1086,
            "group_id": null,
            "aggregate_id": null,
            "round_id": 23332,
            "state_id": 5,
            "venue_id": 618,
            "name": "Esbjerg vs OB",
            "starting_at": "2006-03-25 16:00:00",
            "result_info": "Esbjerg won after full-time.",
            "leg": "1/1",
            "details": null,
            "length": 90,
            "placeholder": false,
            "has_odds": false,
            "has_premium_odds": false,
            "starting_at_timestamp": 1143302400
        }
* */