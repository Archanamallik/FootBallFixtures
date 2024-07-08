package com.example.footballfixtures.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bharadwaj.navigationbarmedium.BottomNavigationItem

import com.example.footballfixtures.screens.CountryScreen
import com.example.footballfixtures.screens.FixtureScreen
import com.example.footballfixtures.screens.LeaguesScreen
import com.example.footballfixtures.screens.PlayersScreen
import com.example.footballfixtures.screens.TeamsScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomBarAnimationApp() {

    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // State of topBar, set state to false, if current page route is "car_details"
    val topBarState = rememberSaveable { (mutableStateOf(true)) }


        val navController = rememberNavController()

        // Subscribe to navBackStackEntry, required to get current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // Control TopBar and BottomBar
        when (navBackStackEntry?.destination?.route) {
            "Fixtures" -> {
                // Show BottomBar and TopBar
                bottomBarState.value = true
                topBarState.value = true
            }
            "Leagues" -> {
                // Show BottomBar and TopBar
                bottomBarState.value = true
                topBarState.value = true
            }
            "Countries" -> {
                // Show BottomBar and TopBar
                bottomBarState.value = true
                topBarState.value = true
            }
            "Teams/{countryId}" -> {
                // Hide BottomBar and TopBar
                bottomBarState.value = false
                topBarState.value = false
            }
            "Leagues/{countryId}" -> {
                // Hide BottomBar and TopBar
                bottomBarState.value = false
                topBarState.value = false
            }
            "Players/{countryId}" -> {
                // Hide BottomBar and TopBar
                bottomBarState.value = false
                topBarState.value = false
            }
        }

        // IMPORTANT, Scaffold from Accompanist, initialized in build.gradle.
        // We use Scaffold from Accompanist, because we need full control of paddings, for example
        // in default Scaffold from Compose we can't disable padding for content from top if we
        // have TopAppBar. In our case it's required because we have animation for TopAppBar,
        // content should be under TopAppBar and we manually control padding for each pages.
       Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            },
            topBar = {
                TopBar(
                    navController = navController,
                    topBarState = topBarState
                )
            },
       ) {paddingValues ->
           NavHost(
               navController = navController,
               startDestination = NavigationItem.Fixtures.route,
               modifier = Modifier.padding(paddingValues = paddingValues)) {
               composable(NavigationItem.Fixtures.route) {
                   // PagingListScreen()
                   FixtureScreen(
                       navController = navController,
                       // snackbarHostState = snackbarHostState,
                   )
               }
               composable(NavigationItem.Leagues.route) {
                   LeaguesScreen(
                       navController = navController,  isCountryId=false
                   )
               }

               composable(NavigationItem.Countries.route) {
                   //val verifyOnClick = remember { mutableStateOf(0) }
                   CountryScreen(
                       navController = navController,

                   ){ countryId: String, index: Int ->
                       //  if(verifyOnClick.value==0)
                       when(index) {
                           0->   navController.navigate("Teams/${countryId}")
                           1->   navController.navigate("Leagues/${countryId}")
                           2->   navController.navigate("Players/${countryId}")
                       }
                   }
               }
               composable(NavigationItem.Teams.route,
                   arguments = listOf(
                   navArgument("countryId"){
                       type = NavType.StringType
                   }
               )) {
                   TeamsScreen(
                       navController = navController,
                   )
                 //  DetailScreen()
               }
              composable(NavigationItem.LeaguesId.route,
                   arguments = listOf(
                       navArgument("countryId"){
                           type = NavType.StringType
                       }
                   )) {
                   LeaguesScreen(
                       navController = navController,
                       isCountryId=true
                   )
                   //  DetailScreen()
               }
               composable(NavigationItem.Players.route,
                   arguments = listOf(
                       navArgument("countryId"){
                           type = NavType.StringType
                       }
                   )) {
                   PlayersScreen(
                       navController = navController,
                   )
                   //  DetailScreen()
               }
           }
       }

}

@Composable
fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {


    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val currentDestination = navBackStackEntry?.destination
                BottomNavigationItem( bottomBarState = bottomBarState).bottomNavigationItems().forEachIndexed { _, navigationItem ->
                    NavigationBarItem(

                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, topBarState: MutableState<Boolean>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val title: String = when (navBackStackEntry?.destination?.route ?: "Fixtures") {
        "Fixtures" -> "Fixtures"
        "Leagues" -> "Leagues"
        "Countries" -> "Countries"
        "Teams" -> "Teams"
        "Players" -> "Players"
        "LeaguesId" -> "Leagues"
        else -> "Fixtures"
    }

    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = { Text(text = title) },
            )
        }
    )
}