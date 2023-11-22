pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "moneymong-android"
include(":app")
include(":data")
include(":domain")
include(":di")
include(":design-system")
include(":feature:sign")
include(":core:ui")
