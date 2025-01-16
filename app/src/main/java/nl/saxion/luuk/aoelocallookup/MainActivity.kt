package nl.saxion.luuk.aoelocallookup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nl.saxion.luuk.aoelocallookup.ui.theme.AOELocalLookupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AOELocalLookupTheme {
                // Set up the NavController and NavHost
                val navController = rememberNavController() // Create NavController

                // Define NavHost that hosts different screens (composables)
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController) // Home screen composable
                    }
                    composable("new_screen") {
                        NewScreen() // New screen composable to navigate to
                    }
                    composable("selector_screen_example"){
                        SelectorExampleScreen() // new example scree
                    }
                    composable("ClickableList") {
                        ClickableList()
                    }
                    composable("ListNavigation") {
                        ListNavigationComponent()
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Greeting(
                name = "Luuk",
                modifier = Modifier.padding(innerPadding)
            )
            Button(
                onClick = {
                    // Navigate to the new screen when the button is clicked
                    navController.navigate("new_screen")
                },
                modifier = Modifier
            ) {
                Text(text = "Go to New Screen")
            }

            Button(
                onClick = {
                    // Navigate to the new screen when the button is clicked
                    navController.navigate("selector_screen_example")
                },
                modifier = Modifier
            ) {
                Text(text = "Go to Selector Example")
            }

            Button(
                onClick = {
                    // Navigate to the new screen when the button is clicked
                    navController.navigate("ClickableList")
                },
                modifier = Modifier
            ) {
                Text(text = "Go to list")
            }

            Button(
                onClick = {
                    // Navigate to the new screen when the button is clicked
                    navController.navigate("ListNavigation")
                },
                modifier = Modifier
            ) {
                Text(text = "Go to list 2")
            }
        }
    }
}

@Composable
fun NewScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text("This is the new screen!")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.DarkGray, modifier = modifier) {
        Text(
            text = "Hello $name!",
            color = Color.LightGray,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AOELocalLookupTheme {
        Greeting("Android")
    }
}
