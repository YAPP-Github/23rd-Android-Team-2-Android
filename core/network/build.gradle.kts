import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.moneymong.android.library)
    alias(libs.plugins.moneymong.android.hilt)
}

android {
    namespace = "com.moneymong.moneymong.network"

    defaultConfig {
        buildConfigField("String", "CLOVA_OCR_DOCUMENT_SECRET", fetchClovaProperties("CLOVA_OCR_DOCUMENT_SECRET"))
        buildConfigField("String", "CLOVA_OCR_DOCUMENT_BASEURL", fetchClovaProperties("CLOVA_OCR_DOCUMENT_BASEURL"))
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    debugApi(libs.chucker.debug)
    releaseApi(libs.chucker.release)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

fun fetchClovaProperties(propertyKey: String) =
    gradleLocalProperties(rootDir).getProperty(propertyKey)