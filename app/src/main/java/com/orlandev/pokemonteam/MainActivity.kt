package com.orlandev.pokemonteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.orlandev.pokemonteam.ui.AppContent
import dagger.hilt.android.AndroidEntryPoint

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
        }
    }
}


/***
 *
 *   val resultTest = remember {
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
 */

