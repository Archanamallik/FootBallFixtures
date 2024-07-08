package com.example.footballfixtures.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.footballfixtures.dataclasses.FixtureItems
import com.example.footballfixtures.viewmodel.FixtureViewModel


@Composable
fun FixtureScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<FixtureViewModel>()
    val fixturelist = viewModel.getFixtureList1().collectAsLazyPagingItems()



    FixtureListContent(
        fixturePagingItems = fixturelist,
        //navigateToDetail = navigateToDetail,
    )
}

@Composable
fun FixtureListContent(
    fixturePagingItems: LazyPagingItems<FixtureItems>,

) {

    val lazyListState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
       if (fixturePagingItems.loadState.refresh is LoadState.Loading) {
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
                    count = fixturePagingItems.itemCount,
                    key = fixturePagingItems.itemKey { it.id },
                ) { index ->
                    val state = rememberSaveable{ mutableStateOf(1)}
                    fixturePagingItems[index]?.let { fixture ->
                        FixtureItemScreen(
                            fixture,
                           // onClick = {
                               // navigateToDetail(fixture.id)
                        //    },
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }
                }
                item {
                    if (fixturePagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

