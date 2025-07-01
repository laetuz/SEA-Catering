package id.neotica.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Splash: Screen()

    // Home Navigation
    @Serializable
    data object HomeScreen: Screen()
    @Serializable
    data object AddChatScreen: Screen()
    @Serializable
    data class Chat(
        val chatId: String = "",
        val user2: String = "",
        val chatroomId: String ?= ""
    )
    @Serializable
    data object UpdateAppScreen: Screen()

    @Serializable
    data object Setting: Screen()

    // Auth Navigation
    @Serializable
    data object LoginScreen: Screen()
    @Serializable
    data object RegisterScreen: Screen()
    @Serializable
    data object ResetPassword: Screen()
    @Serializable
    data object ProfileScreen: Screen()
    @Serializable
    data object AccountSettingScreen: Screen()
    @Serializable
    data class EditProfileScreen(val profPic: String = "")
    @Serializable
    data object InputProfileScreen: Screen()
    @Serializable
    data object VerifyEmailScreen: Screen()
    @Serializable
    data object PrivacyPolicyScreen: Screen()
    @Serializable
    data object TermsOfServiceScreen: Screen()
    @Serializable
    data object DeleteAccountScreen: Screen()
    @Serializable
    data object AboutScreen: Screen()
    @Serializable
    data object CameraPreviewScreen: Screen()

    //Friend Navigation
    @Serializable
    data object FriendScreen: Screen()
    @Serializable
    data object SearchUserScreen: Screen()
    @Serializable
    data class FriendProfileScreen(
        val userId: String
    )

    //Subscription Navigation
    @Serializable
    data object SubscriptionScreen: Screen()
    @Serializable
    data class SubscriptionDetailScreen(val id: Int = 0): Screen()
    @Serializable
    data object SubscriptionPaymentScreen: Screen()
}

@Serializable
sealed class RootScreen {
    @Serializable
    object AuthNav
    @Serializable
    object ProfileNav
    @Serializable
    object HomeNav
}