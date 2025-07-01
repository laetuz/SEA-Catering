import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.compose.compiler)
//    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "authKit"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.core.uiShared)
            implementation(projects.core.domain)
            implementation(projects.core.routes)
            api(projects.core.firebase)
            implementation(projects.core.ktor)
            api("co.touchlab:kermit:2.0.4")
        }
        androidMain.dependencies {
//            implementation(projects.core.core)
//            implementation(projects.core.ui)
        }
        androidUnitTest.dependencies {
//            implementation(libs.bundles.unit.test)
        }
        androidInstrumentedTest.dependencies {
//            implementation(libs.bundles.android.unit.test)
        }
    }
}

android {
    namespace = "id.neotica.auth"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    buildTypes {
//        debug {
////            isMinifyEnabled = true
////            isShrinkResources = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {}