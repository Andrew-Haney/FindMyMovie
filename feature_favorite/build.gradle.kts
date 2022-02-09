plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    // id("kotlin-kapt") GROOVY VERSION
    kotlin("kapt")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(mapOf("path" to ":feature_search")))
    kapt(libs.androidx.room.compiler)
    implementation(libs.bundles.feature.favorite)
}