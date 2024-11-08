import com.jargcode.storechallenge.convention.setAndroidNamespace

plugins {
    alias(libs.plugins.store.android.feature)
}

android {
    setAndroidNamespace("feature.products_list")
}