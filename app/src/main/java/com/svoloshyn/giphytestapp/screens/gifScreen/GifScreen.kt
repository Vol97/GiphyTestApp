package com.svoloshyn.giphytestapp.screens.gifScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun GifScreen(
    gifUrl: String?
) {
    Scaffold(
        content = {
            FullScreenGifImage(url = gifUrl ?: "")
            it
        },
        backgroundColor = Color.Black
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FullScreenGifImage(url: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GlideImage(
            model = url,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentDescription = ""
        )
    }
}