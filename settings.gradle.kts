rootProject.name = "StoreChallenge"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// App
include(":app")

// Core
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:designsystem")
include(":core:ui")
include(":core:testing")