package com.orlandev.pokemonteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orlandev.pokemonteam.ui.theme.PokemonTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val resultTest = remember {
                        mutableStateOf<List<NamedApiResource>?>(null)
                    }

                    val scope = rememberCoroutineScope()

                    LaunchedEffect(Unit) {
                        scope.launch(Dispatchers.IO) {
                            val a = PokeApiClient()
                            val list = a.getRegionList(0, 100)
                            resultTest.value = list.results
                        }

                    }

                    resultTest.value?.let { result ->
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(result) {
                                Text(text = it.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokemonTestTheme {
        Greeting("Android")
    }
}