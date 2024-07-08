package com.example.footballfixtures.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.footballfixtures.R



sealed class NavigationItem(
    val route: String,
    val text:String,
    val icon: ImageVector,
    val title: String,

) {
    object Fixtures : NavigationItem(
        route = "Fixtures",
        title = "Fixtures",
        text="Fixtures",
        icon = Icons.Default.Info
    )

    object Leagues : NavigationItem(
        route = "Leagues",
        title = "Leagues",
        text="Leagues",
        icon = Icons.Default.DateRange
    )

    object Countries : NavigationItem(
        route = "Countries",
        title = "Countries",
        text="Countries",
        icon = Icons.Default.LocationOn
    )
    object Teams : NavigationItem(
        route = "Teams/{countryId}",
        title = "Teams",
        text="Teams",
        icon = Icons.Default.AccountCircle
    )
    object LeaguesId : NavigationItem(
        route = "Leagues/{countryId}",
        title = "Leagues",
        text="Leagues",
        icon = Icons.Default.AccountCircle
    )
    object Players : NavigationItem(
        route = "Players/{countryId}",
        title = "Players",
        text="Players",
        icon = Icons.Default.AccountCircle
    )
}