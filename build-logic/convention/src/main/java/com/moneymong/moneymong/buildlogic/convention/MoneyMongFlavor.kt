package com.moneymong.moneymong.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    deploy
}

@Suppress("EnumEntryName")
enum class MoneyMongFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String) {
    tb(FlavorDimension.deploy, applicationIdSuffix = ".tb"),
    live(FlavorDimension.deploy, applicationIdSuffix = ".live")
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: MoneyMongFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.deploy.name
        productFlavors {
            MoneyMongFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }

                    if (it == MoneyMongFlavor.tb) {
                        manifestPlaceholders["appLabel"] = "머니몽 Dev"
                    } else {
                        manifestPlaceholders["appLabel"] = "머니몽"
                    }
                }
            }
        }
    }
}