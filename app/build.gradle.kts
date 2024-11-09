plugins {
    alias(libs.plugins.store.android.application)
    alias(libs.plugins.store.android.application.compose)
    alias(libs.plugins.store.android.application.flavors)
    alias(libs.plugins.store.hilt)
    alias(libs.plugins.kotlinSerialization)

    // Firebase gradle plugin requires a valid google-services.json file
    // Added for production app demonstration purposes only
    //alias(libs.plugins.store.android.application.firebase)
}

android {
    namespace = "com.jargcode.storechallenge"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    // Modules
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.ui)

    implementation(projects.feature.productsList)
    implementation(projects.feature.cart)

    // Kotlin
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    // Compose
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)

    // Hilt
    implementation(libs.hilt.navigation.compose)
}