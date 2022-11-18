package com.svoloshyn.giphytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.svoloshyn.giphytestapp.ui.theme.GiphyTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val image = "https://media4.giphy.com/media/qJ1IJW0SOtdq8/giphy.gif?cid=aaa2a03950f8k2e7e7ly472a4xu8zmof12b6jxpq0vz81688&rid=giphy.gif&ct=g"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyTestAppTheme {
                val viewModel = hiltViewModel<GiphyViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoadImage(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(viewModel: GiphyViewModel) {
    val imageUrl = viewModel.trendingGifsUrls

    Card(
        modifier = Modifier.size(10.dp),
        shape = RectangleShape,
        elevation = 5.dp
    ) {
        GlideImage(
            model = imageUrl.value?.get(0),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    GiphyTestAppTheme {
//        LoadImage(image)
//    }
//}