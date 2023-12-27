import com.moneymong.moneymong.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("moneymong.android.library")
//                feature/moneymong-90 주석처리
//                apply("moneymong.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:design-system"))
                add("implementation", project(":core:common"))

//                feature/moneymong-90 주석처리
//                add("androidTestImplementation", libs.findLibrary("junit").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}