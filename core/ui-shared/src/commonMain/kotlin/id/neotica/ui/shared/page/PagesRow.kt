package id.neotica.ui.shared.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PagesRow(pages: Int, currentPage: Int, onPageSelected: (Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add margin between items
    ) {
        items(pages) { pageNum ->
            Text(
                text = (pageNum + 1).toString(),
                fontWeight = if (pageNum + 1 == currentPage) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp) // Add padding per item
                    .clickable {
                        onPageSelected(pageNum)
                    }
            )
        }
    }
}