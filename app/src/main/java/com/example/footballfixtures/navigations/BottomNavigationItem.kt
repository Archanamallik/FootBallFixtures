package com.bharadwaj.navigationbarmedium

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.footballfixtures.navigations.NavigationItem

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = "",
    val bottomBarState: MutableState<Boolean>
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Fixture",
                icon = Icons.Filled.Info,
                route = NavigationItem.Fixtures.route,
                bottomBarState = bottomBarState
            ),
            BottomNavigationItem(
                label = "Leagues",
                icon = Icons.Filled.DateRange,
                route = NavigationItem.Leagues.route,
                bottomBarState = bottomBarState
            ),
            BottomNavigationItem(
                label = "Countries",
                icon = Icons.Filled.LocationOn,
                route = NavigationItem.Countries.route,
                bottomBarState = bottomBarState
            ),
        )
    }
}