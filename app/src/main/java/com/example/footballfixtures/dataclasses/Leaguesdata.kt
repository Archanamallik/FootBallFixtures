package com.example.footballfixtures.dataclasses

data class Leaguesdata(
    val data: List<LeaguesItem>
){
    constructor() : this(emptyList())
}