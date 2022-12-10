package com.orlandev.pokemonteam.ui.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.orlandev.pokemonteam.navigation.AppNavigation
import com.orlandev.pokemonteam.ui.common.CardItem
import com.orlandev.pokemonteam.ui.common.PagingView
import com.orlandev.pokemonteam.utils.mappers.asCardModel
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource


@Composable
fun HomeRoute(
    navigateTo: (AppNavigation) -> Unit,
    isExtendedScreen: Boolean,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val regionsList = viewModel.regionList.collectAsLazyPagingItems()
    val isLoading = regionsList.loadState.refresh is LoadState.Loading

    Log.d("SI-regionsList", "isLoading: $isLoading")

    HomeScreen(regionsList, isLoading, navigateTo, isExtendedScreen)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    list: LazyPagingItems<NamedApiResource>,
    isLoading: Boolean,
    navigateTo: (AppNavigation) -> Unit,
    isExtendedScreen: Boolean,
) {
    Scaffold(
        containerColor = Color.Transparent, contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    PagingView(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        list = list,
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(list.itemSnapshotList.toList()) { item ->
                            item?.let {
                                Log.d("NEWS", "List NEWS [ $it ]")
                                CardItem(
                                    modifier = Modifier.fillMaxWidth().height(100.dp).padding(8.dp),
                                    model = it.asCardModel(),
                                    showSubtitle = false,
                                    textAlign = TextAlign.Center,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    titleMaxLine = 1,
                                    titleTextStyle = MaterialTheme.typography.headlineSmall,
                                    subtitleTextStyle = MaterialTheme.typography.bodyMedium,
                                ) {

                                    //TODO ONCLICK NAVIGATE TO FILTER TEAM BY REGIONS

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

