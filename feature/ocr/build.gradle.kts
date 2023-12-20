@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.moneymong.android.library.compose)
    alias(libs.plugins.moneymong.android.feature)
}

android {
    namespace = "com.moneymong.moneymong.ocr"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.cameraX.core)
    implementation(libs.androidx.cameraX.camera2)
    implementation(libs.androidx.cameraX.lifecycle)
    implementation(libs.androidx.cameraX.video)
    implementation(libs.androidx.cameraX.view)
    implementation(libs.androidx.cameraX.extensions)
    implementation(libs.orbit.core)
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewModel)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}