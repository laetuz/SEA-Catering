This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for Kotlin code that’s common for all targets, especially shared Composable UI.
  - Other folders (like `androidMain`, `iosMain`) are for Kotlin code that will be compiled for only the platform indicated in the folder name. For example, `androidMain` contains the Android Application setup and any Android-specific Composables or utilities. `iosMain` in this context would be for iOS-specific Kotlin code related to the shared UI module, though extensive UI work for iOS is typically done in `commonMain` or within the `/iosApp` Xcode project.

* `/iosApp` contains the iOS application. This is the entry point for your iOS app and integrates the shared Kotlin module (which includes `composeApp`). This is also where you would add SwiftUI or UIKit code, manage iOS dependencies, and configure the Xcode project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

# SEA Catering

## Description

SEA Catering is a modern daily catering service that provides a variety of healthy, delicious, and nutritious food packages tailored to customer needs. This service is designed to make it easy for customers to meet their daily consumption needs, whether for individuals, families, or companies.

SEA Catering uses a subscription model with flexible features such as choices of food types, delivery days, and subscription pauses. Its digital system allows users to order, manage, and monitor their subscription status directly from the application, making the catering experience more practical, transparent, and personal.

## Features

### 👋 Welcome Experience

**Splash Screen & Onboarding**  
Making a great first impression and introducing visitors to who we are and what we do.

| Splash | Onboarding 1 | Onboarding 2 | Onboarding 3 |
|--------|---------------|---------------|---------------|
| ![](isi nanti) | ![](isi nanti) | ![](isi nanti) | ![](isi nanti) |


### 👤 Regular User

#### 🔐 User Authentication & Authorization
Login/register with email.

| Login | Register |
|-------|----------|
| ![](isi nanti) | ![](isi nanti) |

#### 🧭 Interactive Navigation
Intuitive interface for exploring catering packages.

| Home | Menu | Subscription Form |
|------|------|-------------------|
| ![](isi nanti) | ![](isi nanti) | ![](isi nanti) |

#### 📝 Add Testimonial
Provide reviews and ratings for the service.

<img src="isi nanti" width="300">

#### 📆 User Can Subscribe
Form to select packages, delivery days, and user's personal data.

<img src="isi nanti" width="300">

#### 📆 View, Pause & Cancel Subscription
Flexibility in managing active subscriptions directly from the dashboard.

<img src="isi nanti" width="300">

### 🛠️ Admin

#### 📊 Admin Dashboard
View statistics such as new subscriptions, MRR, growth, and reactivations.

<img src="isi nanti" width="300">


## Architecture & Tech Stack

- **Primary Language**: Kotlin
- **Architecture**: Kotlin Multiplatform Mobile (KMM), MVVM (Model-View-ViewModel)
- **UI**: Jetpack Compose for Multiplatform (Android & iOS)
- **Navigation**: Jetpack Navigation Compose (for Compose-based UI)
- **Authentication & Database**: Firebase Auth & Firestore (via KMP libraries)
- **Dependency Injection**: Koin
- **Networking**: Ktor HTTP Client
- **Async Operations**: Kotlin Coroutines

## Installation & Getting Started

### Prerequisites

**For Android:**
- Android Studio (latest stable version, e.g., Koala | 2024.1.1 or newer)
- JDK 17 or newer
- Android SDK (minimum API 24)
- Android device or Emulator with API 24 or higher

**For iOS:**
- macOS machine
- Xcode (latest stable version)
- CocoaPods (if not managed by KMM plugin, usually handled automatically)
- iOS device or Simulator

### Clone Repository
```bash
git clone https://github.com/laetuz/SEA-Catering.git
cd SEA-Catering
```

### Running the App

**Android (with Android Studio):**
1. Open the project in Android Studio.
2. Let Android Studio sync the project with Gradle files.
3. Select an Android device or emulator from the target devices dropdown.
4. Click the "Run" button (▶️).

**iOS (with Xcode):**
1. Open the `iosApp/iosApp.xcworkspace` file in Xcode. (Or, if using Fleet or Android Studio with KMM plugin, you might be able to run directly).
2. Select an iOS device or simulator.
3. Click the "Run" button (▶️).
  - **Note:** The first build might take longer as it needs to compile the shared Kotlin module.
  - If you encounter issues with dependencies, ensure CocoaPods are installed and try running `pod install` in the `iosApp` directory from your terminal, then reopen Xcode. However, the KMM Gradle plugin usually handles this.

### Emulator/Simulator Setup

**Android Emulator (with Android Studio):**
1. In Android Studio, open the Device Manager (View > Tool Windows > Device Manager).
2. Click "Create Device".
3. Choose a device definition (e.g., Pixel 6).
4. Select a system image (recommendation: Android 13.0 - API 33 or newer).
5. Complete the setup and launch the emulator.

**iOS Simulator (with Xcode):**
1. In Xcode, go to Window > Devices and Simulators.
2. You can manage existing simulators or add new ones if needed.
3. Select the desired simulator from the scheme's target dropdown before running.

### 🔐 Admin & User Login Credentials
#### 👤 Common user account
- Email: `user@gmail.com`
- Password: `User123!`

#### 🛠️ Admin account
- Email: `admin@gmail.com`
- Password: `Admin123!`

## Project Structure

The project is organized into the following main modules:

```
.
├── composeApp/            # Shared Compose Multiplatform UI and Android application
│   ├── src/
│   │   ├── androidMain/   # Android-specific code and resources
│   │   ├── commonMain/    # Common UI code shared between Android and iOS
│   │   └── iosMain/       # iOS-specific code (rarely used for UI in Compose MP)
│   └── build.gradle.kts
├── iosApp/                # iOS application project
│   ├── iosApp/            # Main iOS application code (SwiftUI, UIKit)
│   └── build.gradle.kts   # (This is a placeholder, iOS uses Xcode build system primarily)
├── core/                  # Core modules shared across features
│   ├── domain/            # Core domain logic and models
│   ├── firebase/          # Firebase integration
│   ├── ktor/              # Ktor HTTP client setup
│   ├── routes/            # Navigation routes and definitions
│   └── ui-shared/         # Shared UI components or utilities (non-Compose specific)
├── feature/               # Feature modules
│   ├── auth/              # Authentication feature
│   └── profile/           # User profile feature
├── build.gradle.kts       # Root project build script
├── settings.gradle.kts    # Project settings, module includes
└── gradle/                # Gradle wrapper and version catalog (libs.versions.toml)
```

**Key Directories:**

*   **`composeApp/src/commonMain`**: This is where most of the shared UI logic resides, written in Kotlin using Jetpack Compose for Multiplatform.
*   **`core/domain/src/commonMain`**: Contains the core business logic, data models, and use cases, shared across all platforms and features.
*   **`feature/*/src/commonMain`**: Each feature module (e.g., `auth`, `profile`) has its own `commonMain` for feature-specific logic and ViewModels.
*   **`composeApp/src/androidMain`**: Android-specific implementations, `AndroidManifest.xml`, resources, and the main Android `Activity`.
*   **`iosApp/`**: The Xcode project for the iOS application. It consumes the shared Kotlin module built from `composeApp` and other `commonMain` sources.

## Contact

Email: laetuzg@gmail.com
GitHub: [laetuz](https://github.com/laetuz)