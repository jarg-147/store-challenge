plugins {
    alias(libs.plugins.store.jvm.library)
    alias(libs.plugins.store.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}