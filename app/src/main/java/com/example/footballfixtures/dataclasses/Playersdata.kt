package com.example.footballdetails.dataclass

import com.example.footballfixtures.dataclasses.Pagination

data class Playersdata(
    val data: List<PlayersItems>?,
    val pagination : Pagination
)

