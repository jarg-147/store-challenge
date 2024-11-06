import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.library.compose)
}

android {
    setAndroidNamespace("core.designsystem")
}

dependencies {
    // Modules
    implementation(projects.core.domain)
    // Dependencies
    api(libs.compose.material3)
    api(libs.compose.material.icons)
    api(libs.compose.runtime)
    api(libs.compose.ui)
}