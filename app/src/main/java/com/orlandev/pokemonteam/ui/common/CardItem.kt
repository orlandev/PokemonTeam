package com.orlandev.pokemonteam.ui.common

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.qamar.elasticview.ElasticView
import java.util.*

data class CardModel(
    val title: String,
    val subtitle: String? = null,
    val image: String? = null, //Image URL
)


@Composable
fun CardItem(
    modifier: Modifier = Modifier.fillMaxWidth(),
    imgSize: Dp = 100.dp,
    model: CardModel,
    titleTextStyle: TextStyle,
    subtitleTextStyle: TextStyle,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.HorizontalOrVertical= Arrangement.Center,
    showSubtitle: Boolean = true,
    titleMaxLine: Int = 1,
    externalShape: Shape = RoundedCornerShape(30),
    internalShape: Shape = RoundedCornerShape(10),
    textAlign: TextAlign,
    onClickAction: () -> Unit
) {
    ElasticView(onClick = onClickAction)
    {
        Card(
            modifier = modifier,
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


                    model.image?.let {
                        val imageResource = model.image.ifEmpty { "" }

                        SubcomposeAsyncImage(model = imageResource,
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

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = if (showSubtitle) Arrangement.spacedBy(10.dp) else verticalArrangement,
                    horizontalAlignment = horizontalAlignment
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f).background(Color.Red)
                            .padding(
                                8.dp
                            ),
                        textAlign = textAlign,
                        text = model.title.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        maxLines = titleMaxLine,
                        overflow = TextOverflow.Ellipsis,
                        style = titleTextStyle,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (showSubtitle) {
                        model.subtitle?.let { subtitle ->
                            Text(
                                modifier = Modifier.weight(1f),
                                text = subtitle,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = subtitleTextStyle,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
