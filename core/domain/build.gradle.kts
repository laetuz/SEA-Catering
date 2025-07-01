import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
//    id("de.mannodermaus.android-junit5") version "1.11.2.0"
//    id("dev.icerock.moko.kswift") version "0.7.0"
//    id("co.touchlab.skie") version "0.9.5"
}

/*kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}*/

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
            baseName = "domain"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
//            implementation(libs.androidx.lifecycle.runtime.ktx)
//            implementation(libs.androidx.lifecycle.viewmodel)
//            api(libs.kotlinx.serialization.json)


        }
        androidUnitTest.dependencies {
//            implementation(libs.junit.jupiter)
//            implementation(libs.mockito.core)
        }
        commonMain.dependencies {
            //put your multiplatform dependencies here
//            api(projects.core.core)
//            implementation(libs.coroutines.core)
            // Koin
            api(libs.bundles.koin)
            api(libs.kotlinx.serialization.json)
            api("co.touchlab:kermit:2.0.4")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "id.neotica.domain"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}