@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.moneymong.android.library)
    alias(libs.plugins.moneymong.android.library.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.moneymong.android.hilt)
    alias(libs.plugins.secretsGradlePlugin)
}

android {
    namespace = "com.moneymong.moneymong.feature.sign"

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(platform(libs.androidx.compose.bom))

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(project(":core:design-system"))
    // 카카오 SDK 의존성
    implementation(libs.kakao.v2.user)

    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.domain)
    implementation(projects.feature.home)

    implementation(libs.orbit.compose)
    implementation(libs.orbit.core)
    implementation(libs.orbit.viewModel)

    implementation(libs.accompanist.systemuicontroller)



}