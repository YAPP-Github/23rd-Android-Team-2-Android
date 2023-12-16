@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.moneymong.android.application)
    alias(libs.plugins.moneymong.android.application.compose)
    alias(libs.plugins.moneymong.android.application.flavors)
    alias(libs.plugins.moneymong.android.application.firebase)
    alias(libs.plugins.moneymong.android.hilt)
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.designSystem)
    implementation(projects.core.common)

    implementation(projects.data)
    implementation(projects.domain)

    implementation(projects.feature.sign)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
}