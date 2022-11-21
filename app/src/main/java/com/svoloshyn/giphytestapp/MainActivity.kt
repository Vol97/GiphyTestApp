package com.svoloshyn.giphytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.svoloshyn.giphytestapp.ui.theme.GiphyTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyTestAppTheme {
                val viewModel = hiltViewModel<GiphyViewModel>()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GiphyScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun GiphyScreen(viewModel: GiphyViewModel) {
    val uiState by viewModel.giphyScreenState.collectAsState()

    Column() {
        SearchView(
            state = uiState,
            viewModel = viewModel
        )

        LazyColumn(
            modifier = Modifier
                .absolutePadding(top = 10.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            items(uiState.gifUrlsList) { item ->
                LoadImage(item)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(
    state: GiphyUiState,
    viewModel: GiphyViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = state.searchText,
        onValueChange = { value ->
            viewModel.updateSearchText(searchString = value)
        },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            IconButton(
                onClick = {
                    viewModel.getGifsBySearch(state.searchText)
                }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            }
        },
        trailingIcon = {
            if (state.searchText != "") {
                IconButton(
                    onClick = {
                        viewModel.updateSearchText(searchString = "")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.getGifsBySearch(state.searchText)
                keyboardController?.hide()
            }
        )
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(url: String) {
    Card(
        modifier = Modifier
            .size(350.dp, 150.dp)
            .padding(all = 5.dp)
    ) {
        GlideImage(
            model = url,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}