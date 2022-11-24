package com.svoloshyn.giphytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.svoloshyn.giphytestapp.navigation.SetupNavGraph
import com.svoloshyn.giphytestapp.ui.theme.GiphyTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyTestAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

