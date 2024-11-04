package com.jarg.transport.porto.convention

import com.android.build.api.dsl.*
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) = with(commonExtension) {
    buildFeatures {
        buildConfig = true
    }

    when (commonExtension) {
        is ApplicationExtension -> {
            extensions.configure<ApplicationExtension> {
                buildTypes {
                    debug {
                        isDebuggable = true
                        isMinifyEnabled = false
                        isShrinkResources = false
                    }

                    release {
                        isDebuggable = false
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }
        }

        is LibraryExtension -> {
            extensions.configure<LibraryExtension> {
                buildTypes {
                    debug {
                        isMinifyEnabled = false
                    }

                    release {
                        isMinifyEnabled = true
                        proguardFiles(
                            commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }
        }
    }
}