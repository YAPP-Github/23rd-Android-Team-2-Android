@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.moneymong.android.library)
    alias(libs.plugins.moneymong.android.hilt)
}

android {
    namespace = "com.moneymong.moneymong.data"
}

dependencies {

    implementation(projects.domain)
    implementation(projects.core.network)
    implementation(libs.retrofit.core)

    // 카카오 SDK 의존성
    implementation(libs.kakao.v2.user)
    implementation(libs.datastore.preferences)
    implementation(libs.datastore.preferences.core)

}