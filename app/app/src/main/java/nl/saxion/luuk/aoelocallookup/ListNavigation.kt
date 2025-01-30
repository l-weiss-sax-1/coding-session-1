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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


val countryData = listOf(
    "Canada" to "Population: 38M\nCapital: Ottawa",
    "China" to "Population: 1.4B\nCapital: Beijing",
    "USA" to "Population: 331M\nCapital: Washington, D.C.",
    "Pakistan" to "Population: 220M\nCapital: Islamabad"
)

@Composable
fun ListNavigationComponent() {
    val navController = rememberNavController()

    // NavHost to handle navigation
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ClickableList(navController = navController)
        }
        composable("details/{country}") { backStackEntry ->
            val country = backStackEntry.arguments?.getString("country") ?: "Unknown"
            DetailsScreen(country)
        }
    }
}

@Composable
fun ClickableList(navController: NavController) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MyList(navController)
        }
    }
}

@Composable
fun MyList(navController: NavController) {

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
                        val country = countryData[index].first
                        navController.navigate("details/$country")
                    },
                shape = CardDefaults.elevatedShape,
                colors = CardDefaults.elevatedCardColors(),
                elevation = CardDefaults.elevatedCardElevation()
            ) {
                Text(
                    text = countryData[index].first,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun DetailsScreen(country: String) {
    val countryDetails = countryData.find { it.first == country }?.second

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Details for $country",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Details: $countryDetails",
                modifier = Modifier.padding(100.dp)
            )
        }
    }
}