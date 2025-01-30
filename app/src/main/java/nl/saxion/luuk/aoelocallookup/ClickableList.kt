package nl.saxion.luuk.aoelocallookup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.runtime.*
import nl.saxion.luuk.aoelocallookup.infrastructure.Api
import nl.saxion.luuk.aoelocallookup.infrastructure.DAO.Civilization


@Composable
fun ClickableList(navController: NavController? = null) {
    Scaffold { innerPadding ->
        // Pass innerPadding to your content to avoid overlap with system UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Adjust for the system UI insets
        ) {
            myList()
        }
    }
}

@Composable
fun myList() {
    // Fetch civilizations data asynchronously
    val api = Api()
    val civilizations = remember { mutableStateOf<List<Civilization>?>(null) }

    // Fetch data in a LaunchedEffect
    LaunchedEffect(Unit) {
        api.fetchCivilizations { civs ->
            civilizations.value = civs
        }
    }

    // State to track expanded items
    var expandedItems by remember { mutableStateOf(setOf<Int>()) }

    // Check if civilizations are loaded
    if (civilizations.value == null) {
        // Display loading text while fetching data
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.background), // Uses background.png
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gray Overlay (50% transparency)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
            )

            // Centered Logo
            Image(
                painter = painterResource(id = R.drawable.logo), // Uses logo.png
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp) // Adjust the size if needed
                    .align(Alignment.Center)
            )
        }
    } else {
        // If civilizations data is available, display them in a LazyColumn
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // Loop through civilizations list
            civilizations.value?.let { civList ->
                items(civList.size) { index ->

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .clickable {
                                expandedItems = if (expandedItems.contains(index)) {
                                    expandedItems - index
                                } else {
                                    expandedItems + index
                                }
                            },
                        shape = CardDefaults.elevatedShape,
                        colors = CardDefaults.elevatedCardColors(),
                        elevation = CardDefaults.elevatedCardElevation()
                    ) {
                        // Display the civilization name
                        Text(
                            text = civList[index].civName,
                            modifier = Modifier.padding(16.dp)
                        )

                        // If the item is expanded, show the units data
                        if (expandedItems.contains(index)) {
                            civList[index].units.forEach { unit ->
                                Text(
                                    text = "${unit.unitName} (Age ID: ${unit.ageId})",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
