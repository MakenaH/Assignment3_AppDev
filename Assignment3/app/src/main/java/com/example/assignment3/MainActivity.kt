package com.example.assignment3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.assignment3.ui.theme.Assignment3Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.Layout.MainLayout



val MagicItems = compositionLocalOf<SnapshotStateList<String>> { SnapshotStateList() }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val magicItems = rememberMutableStateListOf<String>()

                    CompositionLocalProvider(MagicItems provides magicItems) {

                    }

                    Router()

                }
            }
        }
    }
}


