import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.library.flavors)
    alias(libs.plugins.store.hilt)
    alias(libs.plugins.store.android.room)
}

android {
    setAndroidNamespace("core.testing")
}

dependencies {
    // Modules
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.common)
    // Dependencies
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.hilt.android.testing)
    implementation(libs.androidx.runner)
    testImplementation(libs.assertk)
    testImplementation(libs.turbine)
}