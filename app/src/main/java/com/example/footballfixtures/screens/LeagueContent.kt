package com.example.footballfixtures.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.footballfixtures.dataclasses.LeaguesItem

@Composable
fun LeagueContent(
   leagueItems: List<LeaguesItem>, lazyListState: LazyGridState
   ) {

      LazyVerticalGrid(
         columns = GridCells.Fixed(2),
         contentPadding = PaddingValues(8.dp),
         verticalArrangement = Arrangement.SpaceAround,
            state = lazyListState,

         ) {
            items(
               count = leagueItems.size,

            ) { index ->
               val state = rememberSaveable{ mutableStateOf(1)}
               leagueItems[index]?.let { league ->
                  LeagueItemScreen(
                     league,
                     // onClick = {
                     // navigateToDetail(fixture.id)
                     //    },
                     modifier = Modifier.fillMaxWidth(),
                  )


            }

      }
   }
}

