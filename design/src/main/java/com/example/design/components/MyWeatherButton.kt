package com.example.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design.theme.MyWeatherTheme

@Composable
fun MyWeatherButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
){
    Button(
        modifier = modifier
            .padding(
                start = 4.dp,
                end = 8.dp,
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MyWeatherTheme.colors.button,
            contentColor = MyWeatherTheme.colors.mainText,
        ),
        onClick = onClick,
    ) {
        Text(
            text = text,
        )
    }
}
