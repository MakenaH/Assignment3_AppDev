package com.example.assignment3.Layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.assignment3.LocalNavController
/*
    A shared layout to provide some navigational buttons to the top and bottom bars of each of the
    pages of the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    pageTitle: String,
    content: @Composable () -> Unit
) {
    val navController = LocalNavController.current
    Scaffold (
        topBar = { SharedTopBar(pageTitle) },
        bottomBar = { SharedBottomBar()}
       
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}