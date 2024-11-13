import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.library.flavors)
    alias(libs.plugins.store.hilt)
}

android {
    setAndroidNamespace("core.data")
}

dependencies {
    // Modules
    api(projects.core.database)
    api(projects.core.network)
    implementation(projects.core.domain)
}