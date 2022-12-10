package com.orlandev.pokemonteam.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.orlandev.pokemontest.R

@Composable
fun RetryDialog(
    title: String = stringResource(id = R.string.error_title),
    message: String,
    positiveText: String = stringResource(id = R.string.retry),
    negativeText: String = stringResource(id = R.string.cancel),
    onConfirmed: (() -> Unit?)? = null,
    onCancel: (() -> Unit?)? = null
) {
    val isShowDialog = remember { mutableStateOf(true) }
    DialogScreen(
        title = title,
        message = message,
        positiveText = positiveText,
        negativeText = negativeText,
        onConfirmed = onConfirmed,
        isShowDialog = isShowDialog,
        onCancel = onCancel
    )
}

@Composable
fun DialogScreen(
    title: String,
    message: String,
    positiveText: String = stringResource(R.string.yes),
    negativeText: String = stringResource(R.string.no),
    onConfirmed: (() -> Unit?)? = null,
    onCancel: (() -> Unit?)? = null,
    isShowDialog: MutableState<Boolean>
) {
    if (isShowDialog.value) {
        AlertDialog(onDismissRequest = { },
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = {
                Button(onClick = {
                    isShowDialog.value = false
                    onConfirmed?.let { it() }
                }) {
                    Text(text = positiveText)
                }
            },
            dismissButton = {
                Button(onClick = {
                    isShowDialog.value = false
                    onCancel?.let { it() }
                }) {
                    Text(text = negativeText)
                }
            })
    }
}