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

    implementation(libs.paging.runtime)
}