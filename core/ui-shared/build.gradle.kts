import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.moko.resources)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
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
            baseName = "shared"
            isStatic = true
        }
    }
    multiplatformResources {
        resourcesPackage.set("id.neotica.rickpository.res")
    }
    sourceSets {

        androidMain.dependencies {

//            implementation(libs.androidx.lifecycle.runtime.ktx)
//            implementation(libs.androidx.lifecycle.viewmodel)
            //SplashScreen API
//            api(libs.androidx.core.splashscreen)
        }
        androidUnitTest.dependencies {
//            implementation(libs.bundles.unit.test)
        }
        androidInstrumentedTest.dependencies {
//            implementation(libs.bundles.android.unit.test)
        }
        commonMain.dependencies {
            //put your multiplatform dependencies here
//            api(projects.core.core)
            api(libs.droidcore.maven)

            implementation(compose.material3)
            implementation(compose.components.resources)

            api(libs.androidx.navigation.compose)
            //Adaptive
            api(libs.androidx.compose.material3.adaptive)
            //DroidCore
//            api(libs.droidcore)
            // Coil
            implementation(libs.bundles.coil)

            api(libs.moko.resources)
            api(libs.moko.resources.compose)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "id.neotica.ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

//dependencies {
//
////    api("com.valentinilk.shimmer:compose-shimmer:1.3.1")
//
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//}