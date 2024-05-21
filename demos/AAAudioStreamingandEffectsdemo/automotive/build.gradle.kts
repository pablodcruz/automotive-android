// Apply plugins for Android application and Kotlin Android support.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

// Configure the Android build environment.
android {
    // Define the unique package namespace for this app.
    namespace = "com.example.aa_audio_streaming_and_effects_demo"
    // Specify the API level of the Android SDK to compile the app against.
    compileSdk = 34

    // Set up the default configuration for all build variants.
    defaultConfig {
        applicationId = "com.example.aa_audio_streaming_and_effects_demo"
        minSdk = 28  // Minimum Android SDK version the app supports.
        targetSdk = 34  // Target Android SDK version the app is developed and tested against.
        versionCode = 1  // Internal version number used for app updates.
        versionName = "1.0"  // Public version number shown to users.

        // Define the test instrumentation runner class.
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Define build types (e.g., debug, release).
    buildTypes {
        release {
            // Do not shrink, obfuscate, or optimize code in release builds.
            isMinifyEnabled = false
            // Specify ProGuard rules files for code shrinking and obfuscation.
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Specify Java version compatibility for Java source code.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // Specify JVM target version for Kotlin source code.
    kotlinOptions {
        jvmTarget = "1.8"
    }

    // Enable Jetpack Compose features in the build.
    buildFeatures {
        compose = true
    }

    // Configure Kotlin compiler settings for Compose.
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

// Define dependencies for modules, libraries, and testing frameworks.
dependencies {
    // Core library dependencies for AndroidX and Kotlin.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Unit testing dependencies.
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Bill of Materials (BOM) for managing compatible versions of Compose libraries.
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // UI design system libraries (choose one as needed).
    implementation("androidx.compose.material3:material3")
    // implementation("androidx.compose.material:material")
    // implementation("androidx.compose.foundation:foundation")
    // implementation("androidx.compose.ui:ui")

    // Tools for UI development and testing in Android Studio.
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Compose UI testing libraries.
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Optional dependencies for additional functionality.
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3-window-size-class")

    // Integration with Activity and ViewModel architecture components.
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.runtime:runtime-rxjava2")
}
