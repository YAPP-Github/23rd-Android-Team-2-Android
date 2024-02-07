import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.moneymong.android.application)
    alias(libs.plugins.moneymong.android.application.compose)
    alias(libs.plugins.moneymong.android.application.flavors)
    alias(libs.plugins.moneymong.android.application.firebase)
    alias(libs.plugins.moneymong.android.hilt)
    alias(libs.plugins.secretsGradlePlugin)

}

fun getApiKey(propertyKey : String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

android {
    namespace = "com.moneymong.moneymong"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moneymong.moneymong"
        minSdk = 24
        targetSdk = 34
        versionCode = 5
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "NATIVE_APP_KEY", getApiKey("native_app_key"))
    }
    buildFeatures{
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    implementation(projects.core.network)

    implementation(projects.data)
    implementation(projects.domain)

    implementation(projects.feature.sign)
    implementation(projects.feature.ledger)
    implementation(projects.feature.ledgerdetail)
    implementation(projects.feature.ledgermanual)
    implementation(projects.feature.ocr)
    implementation(projects.feature.ocrResult)
    implementation(projects.feature.ocrDetail)
    implementation(projects.feature.member)
    implementation(projects.feature.agency)
    implementation(projects.feature.home)
    implementation(projects.feature.mymong)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.orbit.core)
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewModel)

    implementation(libs.kakao.v2.user)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}