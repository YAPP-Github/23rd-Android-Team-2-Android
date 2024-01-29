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
    implementation(projects.core.common)


    // 카카오 SDK 의존성
    implementation(libs.kakao.v2.user)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
    implementation(libs.paging.runtime)
    implementation(libs.retrofit.core)
}