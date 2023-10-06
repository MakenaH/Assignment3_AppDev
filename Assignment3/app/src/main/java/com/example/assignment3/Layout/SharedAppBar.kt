package com.example.assignment3.Layout


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.assignment3.LocalNavController
/*
    The shared top app bar all screens use, contains a back arrow to navigate to the previous screen
    and displays the passed in title of the page.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTopBar(screenTitle: String){
    val navController = LocalNavController.current
    TopAppBar(title = { Text(text = screenTitle)},
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        })
}