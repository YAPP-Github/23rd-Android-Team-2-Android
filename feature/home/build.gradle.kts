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
    implementation(projects.feature.sign)
    implementation(projects.feature.agency)
    implementation(projects.feature.mymong)
    implementation(projects.feature.ocr)
    implementation(projects.feature.ocrDetail)
    implementation(projects.feature.ocrResult)
    implementation(projects.feature.ledger)
    implementation(projects.feature.ledgerdetail)
    implementation(projects.feature.ledgermanual)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    implementation(libs.accompanist.systemuicontroller)
}