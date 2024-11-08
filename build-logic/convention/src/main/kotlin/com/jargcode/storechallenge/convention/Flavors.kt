package com.jargcode.storechallenge.convention

import com.android.build.api.dsl.*
import com.jargcode.storechallenge.convention.Flavor.*
import com.jargcode.storechallenge.convention.FlavorDimension.env

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
            .forEach { flavor ->
                create(flavor.name) {
                    dimension = flavor.dimension.name

                    if (this@with is ApplicationExtension && this is ApplicationProductFlavor) {
                        flavor.applicationIdSuffix?.let { suffix ->
                            applicationIdSuffix = suffix
                        }
                    }

                    when (flavor) {
                        dev -> {
                            buildConfigField(
                                "String",
                                "BASE_URL",
                                "\"https://gist.githubusercontent.com/palcalde/\""
                            )
                        }

                        staging -> {
                            buildConfigField(
                                "String",
                                "BASE_URL",
                                "\"https://gist.githubusercontent.com/palcalde/\""
                            )
                        }

                        prod -> {
                            buildConfigField(
                                "String",
                                "BASE_URL",
                                "\"https://gist.githubusercontent.com/palcalde/\""
                            )
                        }
                    }
                }
            }
    }
}