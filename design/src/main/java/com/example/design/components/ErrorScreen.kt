package com.example.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.R
import com.example.design.theme.MyWeatherTheme

@Composable
fun ErrorScreen(
    onUpdate: () -> Unit,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = stringResource(R.string.something_went_wrong),
            style = MyWeatherTheme.typography.bodyLarge,
        )

        MyWeatherButton(
            onClick = onUpdate,
            text = stringResource(R.string.update)
        )
    }
}