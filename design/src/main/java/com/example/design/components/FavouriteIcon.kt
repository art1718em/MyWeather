package com.example.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.design.R

@Composable
fun FavouriteIcon(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .size(24.dp),
        painter = if (isFavourite) {
            painterResource(id = R.drawable.ic_unliked)
        } else {
            painterResource(id = R.drawable.ic_liked)
        },
        contentDescription = null,
    )
}