package com.orlandev.pokemonteam.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.qamar.elasticview.ElasticView

data class CardModel(
    val title: String,
    val subtitle: String,
    val image: String, //Image URL
)


@Composable
fun CardItem(
    imgSize: Dp = 100.dp,
    model: CardModel,
    selected: Boolean = false,
    titleMaxLine: Int = 1,
    externalShape: Shape = RoundedCornerShape(10),
    internalShape: Shape = RoundedCornerShape(10),
    onClickAction: () -> Unit
) {
    ElasticView(onClick = onClickAction)
    {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = externalShape,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = internalShape,
                ) {

                    val imageResource = model.image.ifEmpty { "" }

                    SubcomposeAsyncImage(
                        model = imageResource,
                        modifier = Modifier.size(imgSize),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        error = {

                            Text(text = "NO IMAGE")

                        },

                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .placeholder(
                                        visible = true,
                                        color = Color.LightGray,
                                        // optional, defaults to RectangleShape
                                        shape = internalShape,
                                        highlight = PlaceholderHighlight.shimmer(
                                            highlightColor = MaterialTheme.colorScheme.surface,
                                        )
                                    )
                            )
                        })
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = model.title,
                        maxLines = titleMaxLine,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    model.subtitle?.let { subtitle ->
                        Text(
                            text = subtitle,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
