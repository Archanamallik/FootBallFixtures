package com.example.footballfixtures.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.footballfixtures.dataclasses.LeaguesItem
import com.example.footballfixtures.viewmodel.FixtureViewModel


@Composable
fun LeaguesScreen(navController: NavHostController,isCountryId:Boolean) {
   val viewModel = hiltViewModel<FixtureViewModel>()
if(!isCountryId) {
   val leagueList by viewModel.leagueLive.observeAsState(null)

   LaunchedEffect(Unit) {
      viewModel.getLeagues()
   }
   val lazyListState = rememberLazyGridState()

   Column(modifier = Modifier.fillMaxSize()) {
      if (leagueList == null) {
         // Show loading indicator or placeholder
         Text(text = "Loading...")
      } else {
         // Display the list of credit cards
         leagueList?.data?.let { LeagueContent(it, lazyListState) }
      }
   }
}else{
   val leagueList by viewModel.leagueById.observeAsState(null)

   LaunchedEffect(Unit) {
      viewModel.getLeagueById()
   }
   val lazyListState = rememberLazyGridState()

   Column(modifier = Modifier.fillMaxSize()) {
      if (leagueList == null) {
         // Show loading indicator or placeholder
         Text(text = "Loading...")
      } else {
         // Display the list of credit cards
         leagueList?.data?.let { LeagueContent(it, lazyListState) }
      }
   }
}


}

