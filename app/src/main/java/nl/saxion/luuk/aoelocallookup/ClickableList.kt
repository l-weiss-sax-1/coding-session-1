package nl.saxion.luuk.aoelocallookup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.runtime.*


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

    // List of countries with additional data
    val countryData = listOf(
        "Canada" to "Population: 38M\nCapital: Ottawa",
        "China" to "Population: 1.4B\nCapital: Beijing",
        "USA" to "Population: 331M\nCapital: Washington, D.C.",
        "Pakistan" to "Population: 220M\nCapital: Islamabad"
    )

    // State to track expanded items
    var expandedItems by remember { mutableStateOf(setOf<Int>()) }

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        items(countryData.size) { index ->

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

                // Display the country name
                Text(
                    text = countryData[index].first,
                    modifier = Modifier.padding(16.dp)
                )

                // If the item is expanded, show additional data
                if (expandedItems.contains(index)) {
                    Text(
                        text = countryData[index].second,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
