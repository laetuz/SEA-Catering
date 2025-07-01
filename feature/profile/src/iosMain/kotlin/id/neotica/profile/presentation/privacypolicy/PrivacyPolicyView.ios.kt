package id.neotica.profile.presentation.privacypolicy

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import id.neotica.droidcore.component.button.NeoButton
import id.neotica.neoverse.utils.privacyPolicy
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Composable
actual fun PrivacyPolicyView(navController: NavController) {
    NeoButton("Open privacy policy in safari") {
        UIApplication.sharedApplication.openURL(NSURL(string = privacyPolicy))
    }
}