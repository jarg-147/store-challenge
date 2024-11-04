package com.jarg.transport.porto.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.library("compose-bom")
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("implementation", libs.library("compose-ui-tooling-preview"))
            add("debugImplementation", libs.library("compose-ui-tooling"))
        }
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        metricsDestination.set(project.file("compose-metrics"))
        reportsDestination.set(project.file("compose-metrics"))
    }
}
