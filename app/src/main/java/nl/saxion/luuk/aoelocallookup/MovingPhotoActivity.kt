//package nl.saxion.luuk.aoelocallookup
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.size
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.IntOffset
//import kotlinx.coroutines.delay
//import kotlin.math.abs
//import kotlin.math.roundToInt
//import kotlin.random.Random
//
//import androidx.compose.ui.platform.LocalDensity
//
//class MovingPhotoActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MovingPhotoScreen()
//        }
//    }
//}
//
//@Composable
//fun MovingPhotoScreen() {
//    var imageSize1 by remember { mutableStateOf(100.dp) }
//    var imageSize2 by remember { mutableStateOf(100.dp) }
//    var xOffset1 by remember { mutableStateOf(0.dp) }
//    var yOffset1 by remember { mutableStateOf(0.dp) }
//    var xOffset2 by remember { mutableStateOf(250.dp) }
//    var yOffset2 by remember { mutableStateOf(250.dp) }
//    var showHeart by remember { mutableStateOf(false) }
//
//    // Boundaries for movement
//    val xMaxOffset = 300.dp
//    val yMaxOffset = 500.dp
//    val maxSize = 150.dp
//    val minSize = 50.dp
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = painterResource(R.drawable.peter), // Replace with your image resource
//            contentDescription = "Moving Image 1",
//            modifier = Modifier
//                .size(imageSize1)
//                .offset { IntOffset(xOffset1.toPx().roundToInt(), yOffset1.toPx().roundToInt()) },
//            contentScale = ContentScale.Crop
//        )
//        Image(
//            painter = painterResource(R.drawable.tibor), // Replace with your image resource
//            contentDescription = "Moving Image 2",
//            modifier = Modifier
//                .size(imageSize2)
//                .offset { IntOffset(xOffset2.toPx().roundToInt(), yOffset2.toPx().roundToInt()) },
//            contentScale = ContentScale.Crop
//        )
//
//        if (showHeart) {
//            val heartX = ((xOffset1.value + xOffset2.value) / 2) - 25
//            val heartY = ((yOffset1.value + yOffset2.value) / 2) - 25
//            Image(
//                painter = painterResource(android.R.drawable.btn_star_big_on), // Placeholder heart image
//                contentDescription = "Heart Image",
//                modifier = Modifier
//                    .size(50.dp)
//                    .offset { IntOffset(heartX.dp.toPx().roundToInt(), heartY.dp.toPx().roundToInt()) }
//            )
//        }
//
//        LaunchedEffect(Unit) {
//            while (true) {
//                val xDirection1 = if (Random.nextBoolean()) 5.dp else (-5).dp
//                val yDirection1 = if (Random.nextBoolean()) 5.dp else (-5).dp
//                val sizeChange1 = if (Random.nextBoolean()) 5.dp else (-5).dp
//
//                val xDirection2 = if (Random.nextBoolean()) 5.dp else (-5).dp
//                val yDirection2 = if (Random.nextBoolean()) 5.dp else (-5).dp
//                val sizeChange2 = if (Random.nextBoolean()) 5.dp else (-5).dp
//
//                xOffset1 = (xOffset1 + xDirection1).coerceIn(0.dp, xMaxOffset)
//                yOffset1 = (yOffset1 + yDirection1).coerceIn(0.dp, yMaxOffset)
//                imageSize1 = (imageSize1 + sizeChange1).coerceIn(minSize, maxSize)
//
//                xOffset2 = (xOffset2 + xDirection2).coerceIn(0.dp, xMaxOffset)
//                yOffset2 = (yOffset2 + yDirection2).coerceIn(0.dp, yMaxOffset)
//                imageSize2 = (imageSize2 + sizeChange2).coerceIn(minSize, maxSize)
//
//                // Collision detection with px conversion
//                val collisionThreshold = 50.dp.toPx()
//                val xCollision = abs(xOffset1.toPx() - xOffset2.toPx()) < collisionThreshold
//                val yCollision = abs(yOffset1.toPx() - yOffset2.toPx()) < collisionThreshold
//
//                if (xCollision && yCollision) {
//                    showHeart = true
//                    delay(1000) // Show heart for 1 second
//                    showHeart = false
//                }
//
//                delay(16) // 60 FPS equivalent
//            }
//        }
//    }
//}
//
////fun Dp.toPx(): Float = this.value * LocalDensity.current.density
