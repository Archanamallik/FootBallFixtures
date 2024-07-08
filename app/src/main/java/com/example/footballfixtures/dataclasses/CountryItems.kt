package com.example.footballfixtures.dataclasses

data class CountryItems(


    val fifa_name: String,
    val id: String,
    val image_path: String,
    val name: String,
    val official_name: String
)
/*
*{
        "id": 320,
        "continent_id": 1,
        "name": "Denmark",
        "official_name": "Kingdom of Denmark",
        "fifa_name": "DEN",
        "iso2": "DK",
        "iso3": "DNK",
        "latitude": "56.10176086425781",
        "longitude": "9.555907249450684",
        "borders": [
            "DEU"
        ],
        "image_path": "https://cdn.sportmonks.com/images/countries/png/short/dk.png"
    },*/