package com.example.assignment3.Layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.assignment3.LocalNavController
import com.example.assignment3.MagicItems
import com.example.assignment3.R
import com.example.assignment3.Routes


/*
    The shared bottom bar across all the pages in the app. Contains icons to be used to navigate to the
    input form screen, a detail page about the most recently added magic item information if there there
    is any (if not sends empty information to be displayed), the page showing all the added items and
    the contact page.
 */
@Composable
fun SharedBottomBar() {
    val navController = LocalNavController.current
    val magicItems = MagicItems.current
    BottomAppBar(actions = {
        IconButton(onClick = { navController.navigate(Routes.Form.route) }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add item"
            )
        }

        var item : String

        try{
            item = magicItems.last()
        }
        catch (err : Throwable)
        {
            var misc = R.drawable.dndmisc
            item = "none_none_none_none_" + misc
        }

        IconButton(onClick = {navController.navigate(Routes.SingleItem.go(item))}) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Check item"
            )
        }


        IconButton(onClick = {navController.navigate(Routes.ItemsList.route)}) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "List items"
            )
        }

        IconButton(onClick = {navController.navigate(Routes.Contact.route)}) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Contact us"
            )
        }


    }

    )
}