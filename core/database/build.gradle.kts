import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.room)
    alias(libs.plugins.store.android.library.flavors)
    alias(libs.plugins.store.hilt)
}

android {
    setAndroidNamespace("core.database")
}

dependencies {
    // Modules
    implementation(projects.core.domain)
    // Dependencies

}