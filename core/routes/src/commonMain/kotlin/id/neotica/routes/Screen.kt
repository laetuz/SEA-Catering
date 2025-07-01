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
}

@Serializable
sealed class RootScreen {
    @Serializable
    object AuthNav
    @Serializable
    object ProfileNav
    @Serializable
    object HomeNav
    @Serializable
    object WorldNav
    @Serializable
    object FriendNav
    @Serializable
    object AlexandriaNav
}

@Serializable
sealed class WorldScreen {
    @Serializable
    data object HomeScreen: Screen()
    @Serializable
    data object TavernScreen: Screen()
    @Serializable
    data object KanyeScreen: Screen()
    @Serializable
    data object MarketScreen: Screen()
    @Serializable
    data class ItemDetailScreen(val id: String = "", val type: String = "")
    @Serializable
    data object MerchantScreen: Screen()
    @Serializable
    data object HeroMakerScreen: Screen()
    @Serializable
    data object ItemMakerScreen: Screen()
    @Serializable
    data object InventoryScreen: Screen()
    @Serializable
    data object MiningScreen: Screen()
    @Serializable
    data object LeaderBoardsScreen: Screen()
    @Serializable
    data object TerminalScreen: Screen()
    @Serializable
    data object SuperAdminScreen: Screen()
    @Serializable
    data object RavenScreen: Screen()
    @Serializable
    data object RavenChatScreen: Screen()
}

@Serializable
sealed class AlexandriaScreen {
    @Serializable
    data object HomeScreen: AlexandriaScreen()
    @Serializable
    data class BookScreen(val id: String = ""): AlexandriaScreen()
    @Serializable
    data object UserLibraryScreen: AlexandriaScreen()
    @Serializable
    data object AddBookScreen: AlexandriaScreen()
    @Serializable
    data class EditBookScreen(val id: String = ""): AlexandriaScreen()
    @Serializable
    data class EditChapterScreen(val chapterId: Int = 0, val chapterTitle: String = ""): AlexandriaScreen()
    @Serializable
    data class BookContentScreen(val chapterId: Int = 0, val chapterTitle: String = ""): AlexandriaScreen()
    @Serializable
    data object AdminLibraryScreen: AlexandriaScreen()
    @Serializable
    data class AdminBookScreen(val id: String = ""): AlexandriaScreen()
    @Serializable
    data object WarningHubScreen: AlexandriaScreen()
    @Serializable
    data class WarningDetailScreen(val id: String = ""): AlexandriaScreen()
    @Serializable
    data object AdminScreen: AlexandriaScreen()
}