package com.jarg.transport.porto.convention

import com.android.build.api.dsl.*
import com.jarg.transport.porto.convention.FlavorDimension.env

@Suppress("EnumEntryName")
enum class FlavorDimension {
    env
}

@Suppress("EnumEntryName")
enum class Flavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
) {
    dev(dimension = env, applicationIdSuffix = ".dev"),
    staging(dimension = env, applicationIdSuffix = ".staging"),
    prod(dimension = env)
}

internal fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) = with(commonExtension) {
    flavorDimensions += env.name
    productFlavors {
        Flavor
            .values()
            .forEach { flavour ->
                create(flavour.name) {
                    dimension = flavour.dimension.name
                    if (this@with is ApplicationExtension && this is ApplicationProductFlavor) {
                        flavour.applicationIdSuffix?.let { suffix ->
                            applicationIdSuffix = suffix
                        }
                    }
                }
            }
    }
}