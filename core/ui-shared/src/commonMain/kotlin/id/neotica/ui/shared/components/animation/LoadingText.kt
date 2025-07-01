package id.neotica.ui.shared.components.animation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.neotica.ui.shared.theme.DarkPrimaryTransparent80
import kotlinx.coroutines.delay

@Composable
fun LoadingText(
    list: List<String>,
    loop: Boolean = true
) {
    var currentQuoteIndex by remember { mutableIntStateOf(list.indices.random()) }
    var isVisible by remember { mutableStateOf(true) }

    var showCard by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (loop) delay(500) else delay(1000)
        showCard = true
    }

    // Timer to update the quote periodically
    LaunchedEffect(Unit) {
        delay(1000)
        while (true) {
            delay(1500) // Delay for 3 seconds (adjust as needed)
            isVisible = false // Trigger fade-out animation
            delay(500) // Delay for fade-out duration
            currentQuoteIndex = (currentQuoteIndex + list.indices.random()) % list.size
            isVisible = true // Trigger fade-in animation
        }
    }

    if (loop) {
        Box (Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center).size(20.dp).padding(top= 8.dp, bottom = 8.dp))
        }
    }
    androidx.compose.animation.AnimatedVisibility(
        visible = if (loop) isVisible && showCard else showCard,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500)),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                colors = CardDefaults.cardColors().copy(containerColor = DarkPrimaryTransparent80)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = list[currentQuoteIndex],
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}