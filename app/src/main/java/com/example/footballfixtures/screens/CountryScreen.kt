package com.example.footballfixtures.screens

import android.adservices.adid.AdId
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.footballfixtures.dataclasses.CountryItems
import com.example.footballfixtures.viewmodel.FixtureViewModel


@Composable
fun CountryScreen(navController: NavHostController,onClick: (countryId: String,screen:Int) -> Unit) {
   val viewModel = hiltViewModel<FixtureViewModel>()
      val countryList = viewModel.getCountry().collectAsLazyPagingItems()



   countryListContent(
      countryPagingItems = countryList,navController=navController,onClick
      //navigateToDetail = navigateToDetail,
   )
}

@Composable
fun countryListContent(
   countryPagingItems: LazyPagingItems<CountryItems>,
   navController: NavHostController,
   onClick: (countryId: String,screen:Int) -> Unit

) {

   val lazyListState = rememberLazyListState()

   Box(modifier = Modifier.fillMaxSize()) {
      if (countryPagingItems.loadState.refresh is LoadState.Loading) {
         CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      } else {
         LazyColumn(

            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
         ) {
            items(
               count = countryPagingItems.itemCount,
               key = countryPagingItems.itemKey { it.id },
            ) { index ->
               val state = rememberSaveable{ mutableStateOf(1)}
               countryPagingItems[index]?.let { country ->


                  CountryItemScreen(
                     country,
                     // onClick = {
                     // navigateToDetail(fixture.id)
                     //    },
                     modifier = Modifier.fillMaxWidth(),
                     navController=navController, onClick
                  )
               }
            }
           /* item {
               if (countryPagingItems.loadState.append is LoadState.Loading) {
                  CircularProgressIndicator(modifier = Modifier.padding(16.dp))
               }
            }*/
         }
      }
   }
}

