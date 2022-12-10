package com.orlandev.pokemonteam.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.orlandev.pokemontest.R

@Composable
fun <T : Any> PagingView(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<T>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyListScope.() -> Unit
) {

    Crossfade(targetState = list.loadState.refresh is LoadState.Error, animationSpec = tween(700)) {

        if (it) {
            val e = stringResource(id = R.string.error_regions_messages)
            ErrorScreen(message = e, onRetry = list::refresh)
        } else {
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                content()
                list.apply {
                    when {
                        //manage load state when next response page is loading
                        loadState.append is LoadState.Loading -> item {
                            ListLoadingView()
                        }

                        loadState.append is LoadState.Error && loadState.append !is LoadState.Loading -> {
                            val e = loadState.append as LoadState.Error
                            item {
                                ListErrorView(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(18.dp),
                                    message = e.error.message.toString(),
                                    onClick = list::retry
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ListLoadingView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SwipeRefreshIndicator(SwipeRefreshState(true), 50.dp)
    }
}

@Composable
fun ListErrorView(
    modifier: Modifier = Modifier, message: String, onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 30.dp)
    ) {
        Text(
            text = message,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
        )
        Button(
            onClick = onClick
        ) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.btn_retry),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ErrorDialogView(
    throwable: Throwable? = null,
    serverErrorMessage: String?,
    onRetry: () -> Unit,
    onCancel: (() -> Unit)? = null
) {
    if (throwable != null) {
        RetryDialog(
            title = stringResource(id = R.string.error_title),
            message = throwable.message.toString(),
            onConfirmed = onRetry,
            onCancel = onCancel
        )
    } else if (!serverErrorMessage.isNullOrEmpty()) RetryDialog(
        title = stringResource(id = R.string.error_title),
        message = serverErrorMessage,
        onConfirmed = onRetry,
        onCancel = onCancel
    )
}