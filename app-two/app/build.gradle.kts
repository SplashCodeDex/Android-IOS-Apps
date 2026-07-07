plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.apptwo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apptwo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.shared.ui)
    implementation(libs.shared.data)
}
