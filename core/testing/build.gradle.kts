import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.hilt)
}

android {
    setAndroidNamespace("core.testing")
}

dependencies {
    // Modules
    implementation(projects.core.domain)
    implementation(projects.core.common)
    // Dependencies
    implementation(libs.hilt.android.testing)
    api(libs.kotlinx.coroutines.test)
    api(libs.junit5.api)
    api(libs.junit5.engine)
    api(libs.junit5.params)
}