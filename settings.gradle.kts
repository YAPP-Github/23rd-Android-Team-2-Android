pluginManagement {
    includeBuild("build-logic")
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
        maven(url = "https://jitpack.io")
        maven (url = "https://devrepo.kakao.com/nexus/content/groups/public/" )
    }
}

rootProject.name = "moneymong-android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":data")
include(":domain")
include(":core:design-system")
include(":core:ui")
include(":core:common")
include(":core:network")
include(":feature:sign")
include(":feature:ledger")
include(":feature:ocr")
include(":feature:ocr-result")
include(":feature:ocr-detail")
include(":feature:ledgerdetail")
include(":feature:ledgermanual")
include(":feature:mymong")
include(":feature:member")
include(":feature:agency")
include(":feature:home")
