import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.library)
    alias(libs.plugins.store.android.library.compose)
}

android {
    setAndroidNamespace("core.ui")
}

dependencies {

}