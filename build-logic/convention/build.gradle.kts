import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.jargcode.storechallenge.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {

        // region Android plugins

        register("androidApplication") {
            id = "store.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "store.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplicationFirebase") {
            id = "store.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }

        register("androidApplicationFlavors") {
            id = "store.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }

        register("androidHilt") {
            id = "store.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidRoom") {
            id = "store.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidLibrary") {
            id = "store.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "store.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibraryFlavors") {
            id = "store.android.library.flavors"
            implementationClass = "AndroidLibraryFlavorsConventionPlugin"
        }

        // endregion Android plugins

        // region JVM plugins

        register("jvmLibrary") {
            id = "store.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        // endregion JVM plugins

    }
}