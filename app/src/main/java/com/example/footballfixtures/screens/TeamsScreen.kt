package com.example.footballfixtures.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.footballdetails.dataclass.TeamsItems
import com.example.footballfixtures.R
import com.example.footballfixtures.viewmodel.FixtureViewModel


@Composable
fun TeamsScreen(navController: NavHostController) {
   val viewModel = hiltViewModel<FixtureViewModel>()
   val lazyListState = rememberLazyListState()
   // val countryList = viewModel.getCountry().collectAsLazyPagingItems()
   LaunchedEffect(Unit) {
      viewModel.getTeams()
   }
   val teamsList = viewModel.teams.collectAsState()
   LazyColumn( modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      state = lazyListState,
     // contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
      verticalArrangement = Arrangement.spacedBy(5.dp) )
   {
      items(
         count = teamsList.value.size,

         ) { index ->
      //   val state = rememberSaveable { mutableStateOf(1) }
         teamsList.value[index]?.let { country ->
            TeamListItem(country)


         }

      }
   }

}



@Composable
fun TeamListItem(team: TeamsItems) {

   Card(
      modifier = Modifier
         .fillMaxWidth()  .padding(5.dp),

      colors = CardDefaults.cardColors(
         containerColor = colorResource(id= R.color.lavender), //Card background color
         contentColor = Color.Black  //Card content color,e.g.text
      ),
      border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
      content = {
         //Text(text =team.name )
         Row (modifier = Modifier
            .fillMaxWidth()  .padding(5.dp)){
            AsyncImage(
               model = ImageRequest.Builder(LocalContext.current)
                  .data(team.image_path)
                  .crossfade(true)
                  .build(),

               contentDescription = stringResource(R.string.app_name),
               contentScale = ContentScale.Fit,
               modifier = Modifier
                  .size(80.dp)
                  .clip(CircleShape)
                  .border(5.dp, Color.White, CircleShape)
            )
            Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)){
               Text(
                  text = team.name,
                  style = MaterialTheme.typography.headlineLarge,
                  textAlign = TextAlign.Center,
                  modifier = Modifier
                     .padding(all = 8.dp)
                     .align(Alignment.CenterHorizontally)
               )
               Text(
                  text = team.type?:"",
                  style = MaterialTheme.typography.bodyMedium,
                  textAlign = TextAlign.Center,
                  modifier = Modifier
                     .padding(all = 8.dp)
                     .align(Alignment.CenterHorizontally)
               )


            }
         }

      }
   )
}


