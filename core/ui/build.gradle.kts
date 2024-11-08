import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.library.compose)
    alias(libs.plugins.store.android.library.flavors)
}

android {
    setAndroidNamespace("core.ui")
}

dependencies {
    // Modules
    api(projects.core.designsystem)
}