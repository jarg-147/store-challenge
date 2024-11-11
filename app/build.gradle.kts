import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.store.android.application)
    alias(libs.plugins.store.android.application.compose)
    alias(libs.plugins.store.android.application.flavors)
    alias(libs.plugins.store.android.testing)
    alias(libs.plugins.store.hilt)
    alias(libs.plugins.kotlinSerialization)

    // Firebase gradle plugin requires a valid google-services.json file
    // Added for production app demonstration purposes only
    //alias(libs.plugins.store.android.application.firebase)
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.jargcode.storechallenge"

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

dependencies {
    // Modules
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.testing)

    // Features
    implementation(projects.feature.productsList)
    implementation(projects.feature.cart)
    implementation(projects.feature.checkout)

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

    // Test
    debugImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.hilt.android.testing) {
        exclude("androidx.test", "core")
    }
}