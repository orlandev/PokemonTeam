package com.orlandev.pokemonteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.orlandev.pokemonteam.ui.AppContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            val windowSize = calculateWindowSizeClass(this)
            val displayFeatures = calculateDisplayFeatures(this)
            AppContent(windowSizeClass = windowSize, displayFeatures = displayFeatures)
            //TestXXX()
        }
    }
}


@Composable
fun TestXXX() {
    val resultTest = remember {
        mutableStateOf<List<NamedApiResource>?>(null)
    }

    val (offset, setOffset) = remember {
        mutableStateOf(0)
    }
    val (limit, setLimit) = remember {
        mutableStateOf(1)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(limit, offset) {
        scope.launch(Dispatchers.IO) {
            val a = PokeApiClient()
            val list = a.getRegionList(offset, limit)
            resultTest.value = list.results
        }

    }

    resultTest.value?.let { result ->
        Column(
            modifier = Modifier.statusBarsPadding(), verticalArrangement = Arrangement.SpaceAround
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { setOffset(offset + 1) }) {
                    Text(text = "Offset +")
                }

                Button(onClick = { setOffset(offset - 1) }) {
                    Text(text = "Offset -")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { setLimit(limit + 1) }) {
                    Text(text = "Limit +")
                }

                Button(onClick = { setLimit(limit - 1) }) {
                    Text(text = "Limit -")
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                items(result) {
                    Text(text = it.name)
                }
            }
        }
    }
}