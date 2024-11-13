plugins {
    alias(libs.plugins.store.jvm.library)
    alias(libs.plugins.store.hilt)
}

dependencies {
    // Modules
    implementation(projects.core.domain)

    // Dependencies
    implementation(libs.kotlinx.coroutines.core)
}