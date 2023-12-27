@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.moneymong.android.library.compose)
    alias(libs.plugins.moneymong.android.feature)
    alias(libs.plugins.moneymong.android.hilt)
}

android {
    namespace = "com.moneymong.moneymong.home"
}

dependencies {

    implementation(projects.core.designSystem)
    implementation(projects.feature.agency)
    implementation(projects.feature.ledger)
    implementation(projects.feature.mymong)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}