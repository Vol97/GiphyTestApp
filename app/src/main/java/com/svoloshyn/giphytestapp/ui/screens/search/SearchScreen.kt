package com.svoloshyn.giphytestapp.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.svoloshyn.giphytestapp.models.GeneralGifData
import com.svoloshyn.giphytestapp.models.GifImageData
import com.svoloshyn.giphytestapp.navigation.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: GiphyViewModel = hiltViewModel()
) {
    val uiState by viewModel.searchString.collectAsState()
    val gifUrls = viewModel.searchedGifs.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchView(searchString = uiState.searchString,
                onTextChange = {
                    viewModel.updateSearchString(searchString = it)
                },
                onSearchClicked = {
                    viewModel.searchGifs(searchString = it)
                }
            )
        },
        content = {
            ListContent(
                items = gifUrls,
                navController = navController,
                paddingValues = it
            )
        },
        backgroundColor = Color.Black
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(
    searchString: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .semantics {
                contentDescription = "SearchView"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.background
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "TextField"
                },
            value = searchString,
            onValueChange = { onTextChange(it) },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onBackground
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = ContentAlpha.medium),
                    onClick = {
                        onSearchClicked(searchString)
                        keyboardController?.hide()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "SearchIcon",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .semantics {
                            contentDescription = "ClearButton"
                        },
                    onClick = {
                        if (searchString.isNotEmpty()) {
                            onTextChange("")
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "ClearIcon",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(searchString)
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Gray,
                cursorColor = MaterialTheme.colors.primary
            )
        )
    }
}

@Composable
fun ListContent(
    items: LazyPagingItems<GeneralGifData>,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = items,
            key = { gifImage ->
                gifImage.id
            }
        ) { gifImage ->
            gifImage?.let {
                LoadImage(
                    gifImageData = it.images,
                    navController = navController
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(
    gifImageData: GifImageData,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(
                    gifImageData.original.url,
                    StandardCharsets.UTF_8.toString()
                )
                navController.navigate(Screen.Gif.withArgs(encodedUrl))
            }
    ) {
        GlideImage(
            model = gifImageData.downsized.downsizedUrl,
            modifier = Modifier.align(Alignment.Center),
            contentDescription = ""
        )
    }
}