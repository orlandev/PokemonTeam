package com.orlandev.pokemonteam.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.orlandev.pokemonteam.navigation.AppNavigation
import com.orlandev.pokemonteam.ui.common.CardItem
import com.orlandev.pokemonteam.ui.common.CardModel
import com.orlandev.pokemonteam.ui.common.PagingView
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

@OptIn(ExperimentalMaterial3Api::class)
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

        Surface(modifier = Modifier.fillMaxSize()) {
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
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        items(list) { item ->
                            item?.let {
                                Log.d("NEWS", "List NEWS [ $it ]")
                                CardItem(
                                    model = it.asCardModel()
                                ) {
                                }

                                //TODO MAKE NAVIGATION TO

                            }
                        }
                    }

                }
            }
        }
    }
}

fun NamedApiResource.asCardModel(): CardModel {
    return CardModel(
        title = this.name,
        subtitle = this.category,
        image = ""
    )
}