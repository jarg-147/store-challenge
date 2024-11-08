import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.library.flavors)
    alias(libs.plugins.store.hilt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    setAndroidNamespace("core.network")
}

dependencies {
    // Modules
    implementation(projects.core.domain)
    // Dependencies
    implementation(libs.retrofit)
    implementation(libs.retrofit.okhttp)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
}