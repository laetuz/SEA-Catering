package id.neotica.profile.presentation.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.neotica.ui.shared.components.BasicScaffold
import id.neotica.ui.shared.theme.DarkPrimary

@Composable
fun AboutView(navController: NavController) {
    BasicScaffold(
        topBarTitle = "About",
        true,
        navController = navController,
    ) {
//        val context = LocalContext.current

        LazyColumn(Modifier.padding(vertical = 4.dp, horizontal = 16.dp)) {
            item {
                Text("This project is created by Ryo Martin Sopian.")
                Spacer(Modifier.height(8.dp))
                Text("Contributor: ")
                Column(
                    Modifier.padding(start = 2.dp)
                ) {
                    Text(
                        color = DarkPrimary,
                        fontSize = 14.sp,
                        text = "- laetuz",
//                        modifier = Modifier.clickable {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/laetuz"))
//                            context.startActivity(intent)
//                        }
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text("Some icons may use assets from (and free for commercial uses): ")
                Column(
                    Modifier.padding(start = 2.dp)
                ) {
                    Text(
                        color = DarkPrimary,
                        fontSize = 14.sp,
                        text = "- Streamline",
//                        modifier = Modifier.clickable {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://streamlinehq.com"))
//                            context.startActivity(intent)
//                        }
                    )
                    Text(
                        color = DarkPrimary,
                        fontSize = 14.sp,
                        text = "- Glitch",
//                        modifier = Modifier.clickable {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://archive.org/details/glitch-public-domain-game-art"))
//                            context.startActivity(intent)
//                        }
                    )
                    Text(
                        color = DarkPrimary,
                        fontSize = 14.sp,
                        text = "- Caner Kaşeler",
//                        modifier = Modifier.clickable {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/canerkaseler/jetpack-compose-threads-card"))
//                            context.startActivity(intent)
//                        }
                    )
                }
            }
        }
    }
}