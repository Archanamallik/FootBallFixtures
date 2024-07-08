package com.example.footballfixtures.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.footballdetails.dataclass.PlayersItems
import com.example.footballfixtures.viewmodel.FixtureViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayersScreen(navController: NavHostController) {
   val viewModel = hiltViewModel<FixtureViewModel>()

   val playersList = viewModel.getPlayers()?.collectAsLazyPagingItems()
   val coachList = viewModel.getCoaches()?.collectAsLazyPagingItems()
   val refereeList = viewModel.getReferees()?.collectAsLazyPagingItems()


Column ( modifier = Modifier.verticalScroll(rememberScrollState())
  ){
   Text(
      text = "Players",

      color = Color.Black,
      modifier = Modifier.padding(0.dp, 2.dp),
      style = MaterialTheme.typography.headlineLarge
   )
   Spacer(modifier = Modifier.height(15.dp))
   playerListContent(
      playerPagingItems = playersList,
      //navigateToDetail = navigateToDetail,
   )
   Text(
      text = "Coaches",

      color = Color.Black,
      modifier = Modifier.padding(0.dp, 2.dp),
      style = MaterialTheme.typography.headlineLarge
   )
   Spacer(modifier = Modifier.height(15.dp))
   playerListContent(
      playerPagingItems = coachList,
      //navigateToDetail = navigateToDetail,
   )
   Text(
      text = "Referees",

      color = Color.Black,
      modifier = Modifier.padding(0.dp, 2.dp),
      style = MaterialTheme.typography.headlineLarge
   )
   Spacer(modifier = Modifier.height(15.dp))
   playerListContent(
      playerPagingItems = refereeList,
      //navigateToDetail = navigateToDetail,
   )
}
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun playerListContent(
   playerPagingItems: LazyPagingItems<PlayersItems>?,

   ) {

   val lazyListState = rememberLazyListState()

   Box(modifier = Modifier.fillMaxWidth()) {
      if (playerPagingItems?.loadState?.refresh is LoadState.Loading) {
         CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      } else {
         LazyRow(

            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            verticalAlignment = Alignment.Top
         ) {
            playerPagingItems?.itemCount?.let {
               items(
                  count = it,
                  key = playerPagingItems?.itemKey { it.id },
               ) { index ->
                  val state = rememberSaveable{ mutableStateOf(1)}
                  playerPagingItems?.get(index)?.let { player ->
                     PlayerItemScreen(
                        player,
                        // onClick = {
                        // navigateToDetail(fixture.id)
                        //    },
                        modifier = Modifier
                     )

                  }
               }
            }
            item {
               if (playerPagingItems?.loadState?.append is LoadState.Loading) {
                  CircularProgressIndicator(modifier = Modifier.padding(16.dp))
               }
            }
         }
      }
   }
}

