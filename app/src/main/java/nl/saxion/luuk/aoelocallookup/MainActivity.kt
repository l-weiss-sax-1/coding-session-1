package nl.saxion.luuk.aoelocallookup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

                // Define NavHost that hosts different screens (composable)
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
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = {
                    navController.navigate("new_screen")
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(AndroidColor.parseColor("#303030")),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Go to New Screen")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("selector_screen_example")
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(AndroidColor.parseColor("#303030")),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Go to Selector Example")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("ClickableList")
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(AndroidColor.parseColor("#303030")),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Go to list")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("ListNavigation")
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(AndroidColor.parseColor("#303030")),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
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
