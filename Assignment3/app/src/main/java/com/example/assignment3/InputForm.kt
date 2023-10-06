package com.example.assignment3


import android.app.AlertDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/*
 * Provides an input form for a user to input the name, source book, rarity and description of a Dungeons and Dragons
 * magic item. Contains two buttons, one to add the item to the list of items, and the other to clear the
 * current item information from the text fields. Adding an item brings up a a more detailed page about the item
 * that also allows the user to delete it.
 * Does not allow for underscores or empty fields to be inputted, and calls the composable InvalidFormInput to
 * inform the user they cannot do whichever they were attempting with a passed in error message.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm() {
    var name by rememberSaveable { mutableStateOf("") }
    var sourceBook by rememberSaveable { mutableStateOf("") }
    var rarity by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var selectedIndex by remember { mutableStateOf(0) }
    val navController = LocalNavController.current
    val itemsList = MagicItems.current

    Column {
        var invalidInput by rememberSaveable { mutableStateOf(false) }
        var errorMessage by rememberSaveable { mutableStateOf("") }

        Card(border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary), colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.secondaryContainer)) {
            Text("Input cannot include underscores.",
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), color = MaterialTheme.colorScheme.secondary)
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
        )
        Row{
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                value = name,
                singleLine = true,
                onValueChange = {
                    if(!it.contains("_")) {
                        name = it
                    }
                    else
                    {
                        invalidInput = true
                        errorMessage = "Input cannot have underscores"
                    }},

                label = { Text("Item name") }
            )
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                value = sourceBook,
                singleLine = true,
                onValueChange =  {
                    if(!it.contains("_")) {
                        sourceBook = it
                    }
                    else
                    {
                        invalidInput = true
                        errorMessage = "Input cannot have underscores"
                    }},
                label = { Text("Item source") }
            )

            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                value = rarity,
                singleLine = true,
                onValueChange =  {
                    if(!it.contains("_")) {
                        rarity = it
                    }
                    else
                    {
                        invalidInput = true
                        errorMessage = "Input cannot have underscores"
                    }},
                label = { Text("Item rarity") }
            )
        }
        Row{
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                value = description,
                onValueChange =  {
                    if(!it.contains("_")) {
                        description = it
                    }
                    else
                    {
                        invalidInput = true
                        errorMessage = "Input cannot have underscores"
                    }},
                label = { Text("Item description") },

            )


        }
        Spacer(
            modifier = Modifier
                .height(5.dp)
        )

        val pictures = listOf(R.drawable.dndaxe, R.drawable.dndbag, R.drawable.dndbook, R.drawable.dndbow, R.drawable.dndpotion, R.drawable.dndshield, R.drawable.dndmisc)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
            //border =BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(2.dp)){
            Text("Type of Magic Item Image", modifier = Modifier.padding(3.dp))

                var expanded by remember { mutableStateOf(false) }
                val items = listOf("Axe", "Bag", "Book", "Bow", "Potion", "Shield", "Other")




                Box(modifier = Modifier.padding(5.dp).border(BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)).padding(1.dp)) {
                    Text(items[selectedIndex],modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { expanded = true })
                        .background(
                            MaterialTheme.colorScheme.tertiaryContainer
                        ))
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.tertiaryContainer
                            )
                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(onClick = {
                                selectedIndex = index
                                expanded = false
                            }, text = { Text(s)})
                        }
                    }
                }

        }


        Row{
            Button(onClick = {
                if(name == "" || rarity == "" || sourceBook == "" || description == "")
                {
                    invalidInput = true
                    errorMessage = "No boxes may be empty."
                }
                else
                {
                    itemsList.add("${name}_${sourceBook}_${rarity}_${description}_${pictures[selectedIndex]}")
                    navController.navigate(Routes.SingleItem.go("${name}_${sourceBook}_${rarity}_${description}_${pictures[selectedIndex]}"))

                }
            })
            {
                Text("Add Item")
            }
            Button(onClick = {
                name= ""
                rarity = ""
                description = ""
                sourceBook = ""})
            {
                Text("Clear Item Information")
            }
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
        )

        if(invalidInput)
        {
            invalidInput = false;
            InvalidFormInput(errorMessage)
        }


    }
}

/*
 * Creates a dialog box that displays the passed in error message about invalid input. Can be closed
 * by clicking on the screen behind it or clicking the Close button on the box.
 */

@Composable
fun InvalidFormInput(errorMessage: String)
{
    val builder = AlertDialog.Builder(LocalContext.current)
    builder.setTitle("Invalid Input")
    builder.setMessage(errorMessage)
    builder.setNeutralButton("Close"){ _, _ -> }

    builder.show()
}
