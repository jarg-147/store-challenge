package com.jarg.transport.porto.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.version(name: String): String = findVersion(name).get().toString()

fun VersionCatalog.library(name: String) = findLibrary(name).get()

fun VersionCatalog.plugin(name: String): String = findPlugin(name).get().get().pluginId

fun VersionCatalog.bundle(name: String) = findBundle(name).get()