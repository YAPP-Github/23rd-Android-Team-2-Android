@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.moneymong.android.application)
    alias(libs.plugins.moneymong.android.application.compose)
    alias(libs.plugins.moneymong.android.application.flavors)
//    alias(libs.plugins.moneymong.android.hilt) hilt compiler was found error
}

android {
    namespace = "com.moneymong.moneymong"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moneymong.moneymong"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

//    flavorDimensions += "deploy"
//    productFlavors {
//        create("tb") {
//            dimension = "deploy"
//            applicationIdSuffix = ".tb"
//
//            manifestPlaceholders["appLabel"] = "머니몽 TB"
//            buildConfigField("Boolean", "IS_TB", "true")
//        }
//        create("live") {
//            dimension = "deploy"
//            applicationIdSuffix = ".live"
//
//            manifestPlaceholders["appLable"] = "머니몽"
//            buildConfigField("Boolean", "IS_TB", "false")
//        }
//    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)

    implementation(projects.data)
    implementation(projects.domain)
    
    implementation(projects.feature.sign)
}