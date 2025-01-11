package nl.saxion.luuk.aoelocallookup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import android.util.Log // Import for logging


@Composable
fun SelectorExampleScreen(navController: NavController? = null) {
    Scaffold { innerPadding ->
        // Pass innerPadding to your content to avoid overlap with system UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Adjust for the system UI insets
        ) {
            SelectorExampleContent()
        }
    }
}

@Composable
fun SelectorExampleContent() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically // center all elements center with row
    ) {
        Text(
            text = "Label:",
            style = MaterialTheme.typography.bodyLarge
        )
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                Log.d("SelectorExample", "Text changed: ${it.text}") // Log text changes

            },
            label = { Text("Enter text") },
            modifier = Modifier.weight(1f) // Ensures the field takes available space
        )
    }
}
