package com.example.assignment3

import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.Layout.MainLayout

val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController found!") }

/*
    Provides the navigation functionality between different screens.
 */
@Composable
fun Router() {
    val navController = rememberNavController()


    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = "FormRoute") {
            composable(Routes.Form.route) {
                MainLayout(pageTitle = "Form") {
                    InputForm()
                }
            }
            composable(Routes.ItemsList.route) {
                MainLayout(pageTitle = "List") {
                    ItemScreen()
                }
            }
            composable(Routes.SingleItem.route) {
                MainLayout(pageTitle = "Item") {
                    DetailScreen(items = it.arguments?.getString("items") ?: "")
                }
            }
            composable(Routes.Contact.route){
                MainLayout(pageTitle = "Contact Us") {
                    ContactScreen()
                }
            }

        }
    }

}


/*
    Defines the routes to the different screens.
 */
sealed class Routes(val route:String)  {
    object Form : Routes("FormRoute")
    object SingleItem : Routes("SingleItemRoute/{items}") {
        fun go(items: String) = "SingleItemRoute/$items"
    }
    object ItemsList: Routes("ItemsListRoute")
    object Contact: Routes("ContactRoute")
}
